package jetbrains.buildServer.rust

import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildRunnerContextEx
import jetbrains.buildServer.agent.inspections.InspectionReporter
import jetbrains.buildServer.agent.runner.*
import jetbrains.buildServer.rust.inspections.ClippyInspectionsParser
import jetbrains.buildServer.util.FileUtil
import jetbrains.buildServer.util.OSType
import java.io.File
import java.util.*
import java.util.concurrent.atomic.AtomicReference

/**
 * Cargo runner service.
 */
class CargoCommandBuildSession(
    private val runnerContext: BuildRunnerContextEx,
    private val inspectionReporter: InspectionReporter,
    private val clippyInspectionsParser: ClippyInspectionsParser
) : MultiCommandBuildSession {

    companion object {
        private val LOG = Logger.getInstance(AbstractToolProvider::class.java.name)
    }

    private var buildSteps: Iterator<CommandExecution>? = null
    private val executionResult: AtomicReference<BuildFinishedStatus> = AtomicReference(BuildFinishedStatus.FINISHED_SUCCESS)
    private val filesToClean = mutableListOf<File>()

    override fun sessionStarted() {
        buildSteps = getSteps()
    }

    override fun getNextCommand(): CommandExecution? {
        buildSteps?.let {
            if (it.hasNext()) {
                return it.next()
            }
        }

        return null
    }

    override fun sessionFinished(): BuildFinishedStatus {
        cleanFiles()
        return executionResult.get()
    }

    private fun getSteps(): Iterator<CommandExecution> = iterator {
        val parameters = runnerContext.runnerParameters
        val command = parameters[CargoConstants.PARAM_COMMAND]

        if (!runnerContext.isVirtualContext) {
            val toolchainVersion = parameters[CargoConstants.PARAM_TOOLCHAIN]?.takeIf(String::isNotBlank)?.trim()

            toolchainVersion?.let { requestedVersion ->
                val installToolchain = RustupToolchainBuildService("install", requestedVersion)
                yield(createCommand(installToolchain))

                // `Rustup` could fail to install a toolchain.
                // We can try to resolve it by uninstalling the toolchain and cleaning up temporary directories.
                if (installToolchain.hasErrors()) {
                    val logger = runnerContext.build.buildLogger
                    logger.message("Installation has failed, will remove toolchain '${installToolchain.version}' and try again")

                    yield(createCommand(RustupToolchainBuildService("uninstall", requestedVersion)))

                    val rustupCache = RustupToolProvider.getHome()
                    // Cleanup temp directories
                    FileUtil.delete(File(rustupCache, CargoConstants.RUSTUP_DOWNLOADS_DIR))
                    FileUtil.delete(File(rustupCache, CargoConstants.RUSTUP_TMP_DIR))
                    // Remove toolchain files
                    FileUtil.delete(File(rustupCache, "${CargoConstants.RUSTUP_TOOLCHAINS_DIR}/$installToolchain.version"))
                    FileUtil.delete(File(rustupCache, "${CargoConstants.RUSTUP_HASHES_DIR}/$installToolchain.version"))

                    val reinstallToolchain = RustupToolchainBuildService("install", requestedVersion)
                    yield(createCommand(reinstallToolchain))
                    if (reinstallToolchain.hasErrors()) {
                        return@iterator
                    }
                }
            }

            val targetParamName = "cargo-$command-target"
            parameters[targetParamName]?.takeIf(String::isNotBlank)?.trim()?.let { targetTriple ->
                val addTarget = RustupTargetBuildService(targetTriple, toolchainVersion)
                yield(createCommand(addTarget))
                if (addTarget.hasErrors()) {
                    return@iterator
                }
            }
        }

        val commands = mutableListOf<CommandExecution>()

        if (command == CargoConstants.COMMAND_CLIPPY) {
            commands.add(createCommand(RustupComponentAddBuildService("clippy")))
        }

        if (command == CargoConstants.COMMAND_CUSTOM_CRATE) {
            val command = parameters[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
            commands.add(createCommand(CargoInstallCrateService("cargo-$command")))
        }

        commands.add(createCommand(CargoRunnerBuildService(runnerContext, inspectionReporter, clippyInspectionsParser), true))

        if (runnerContext.isVirtualContext) {
            yield(MultiCommandExecution(commands, runnerContext, filesToClean))
        } else {
            yieldAll(commands)
        }
    }

    private fun createCommand(buildService: CommandLineBuildService, redirectStderrToStdout: Boolean = false) = CommandExecutionAdapter(buildService.apply {
        this.initialize(runnerContext.build, runnerContext)
    }, redirectStderrToStdout, executionResult)

    private fun cleanFiles() {
        filesToClean.forEach {
            try {
                it.delete()
            } catch (e: Throwable) {
                LOG.error("Can't remove file ${it.absoluteFile} after build finished", e)
            }
        }
    }
}

private class MultiCommandExecution(
    private val commands: List<CommandExecution>,
    private val runnerContext: BuildRunnerContextEx,
    private val filesToClean: MutableList<File>
) : CommandExecution {
    override fun makeProgramCommandLine(): ProgramCommandLine =
        MultiProgramCommandLine(commands.map { it.makeProgramCommandLine() }, runnerContext, filesToClean)
    override fun onStandardOutput(text: String) = last { it.onStandardOutput(text) }
    override fun onErrorOutput(text: String) = last { it.onErrorOutput(text) }
    override fun processStarted(programCommandLine: String, workingDirectory: File) =
        last { it.processStarted(programCommandLine, workingDirectory) }
    override fun processFinished(exitCode: Int) = last { it.processFinished(exitCode) }
    override fun beforeProcessStarted() = last { it.beforeProcessStarted() }
    override fun interruptRequested(): TerminationAction {
        var result = TerminationAction.NONE
        for (action in commands.map { it.interruptRequested() }) {
            if (action == TerminationAction.KILL_PROCESS_TREE) {
                result = action
                break
            }
            if (action == TerminationAction.KILL_CREATED_PROCESS) {
                result = action
            }
        }
        return result
    }
    override fun isCommandLineLoggingEnabled(): Boolean = last { it.isCommandLineLoggingEnabled }
    private fun <T> last(block: (CommandExecution) -> T): T = block(commands.last())
}

private class MultiProgramCommandLine(
    private val commands: List<ProgramCommandLine>,
    private val runnerContext: BuildRunnerContextEx,
    private val filesToClean: MutableList<File>
) : ProgramCommandLine {
    override fun getExecutablePath(): String {
        val script = File(runnerContext.workingDirectory, "multi-command-line-${UUID.randomUUID()}.cmd")
        val stream = script.outputStream().bufferedWriter()
        for (command in commands) {
            stream.appendLine("cd ${command.workingDirectory}")
            val argumentLine = when (runnerContext.virtualContext.targetOSType) {
                OSType.WINDOWS -> command.arguments.joinToString(" ")
                else -> command.arguments.joinToString(separator = "\" \"", prefix = "\"", postfix = "\"")
            }
            stream.appendLine("${command.executablePath} $argumentLine")
        }
        stream.close()
        script.setExecutable(true)
        filesToClean.add(script)
        return script.absolutePath
    }
    override fun getWorkingDirectory(): String = runnerContext.workingDirectory.absolutePath
    override fun getArguments(): List<String> = emptyList()
    override fun getEnvironment(): Map<String, String> = commands.map { it.environment }.fold(emptyMap()) { m1, m2 -> m1 + m2 }
}
