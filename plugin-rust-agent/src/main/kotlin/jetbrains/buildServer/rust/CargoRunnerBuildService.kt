package jetbrains.buildServer.rust

import com.github.zafarkhaja.semver.Version
import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildRunnerContextEx
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.inspections.InspectionReporter
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.cargo.*
import jetbrains.buildServer.rust.inspections.ClippyInspectionsParser
import jetbrains.buildServer.rust.inspections.ClippyListener
import jetbrains.buildServer.rust.logging.BlockListener
import jetbrains.buildServer.rust.logging.CargoLoggerFactory
import jetbrains.buildServer.rust.logging.CargoLoggingListener
import jetbrains.buildServer.util.OSType
import jetbrains.buildServer.util.StringUtil
import java.io.File

/**
 * Cargo runner service.
 */
class CargoRunnerBuildService(
        private val runnerContext: BuildRunnerContextEx,
        private val inspectionReporter: InspectionReporter,
        private val clippyInspectionsParser: ClippyInspectionsParser
) : BuildServiceAdapter() {

    private val myCargoWithStdErrVersion = Version.forIntegers(0, 13)
    private val myArgumentsProviders = mapOf(
            CargoConstants.COMMAND_BENCH to BenchArgumentsProvider(),
            CargoConstants.COMMAND_BUILD to BuildArgumentsProvider(),
            CargoConstants.COMMAND_CHECK to CheckArgumentsProvider(),
            CargoConstants.COMMAND_CLEAN to CleanArgumentsProvider(),
            CargoConstants.COMMAND_CLIPPY to ClippyArgumentsProvider(),
            CargoConstants.COMMAND_DOC to DocArgumentsProvider(),
            CargoConstants.COMMAND_LOGIN to LoginArgumentsProvider(),
            CargoConstants.COMMAND_PACKAGE to PackageArgumentsProvider(),
            CargoConstants.COMMAND_PUBLISH to PublishArgumentsProvider(),
            CargoConstants.COMMAND_RUN to RunArgumentsProvider(),
            CargoConstants.COMMAND_RUSTC to RustcArgumentsProvider(),
            CargoConstants.COMMAND_RUSTDOC to RustDocArgumentsProvider(),
            CargoConstants.COMMAND_TEST to TestArgumentsProvider(),
            CargoConstants.COMMAND_UPDATE to UpdateArgumentsProvider(),
            CargoConstants.COMMAND_YANK to YankArgumentsProvider(),
            CargoConstants.COMMAND_CUSTOM_CRATE to CustomCrateArgumentsProvider()
    )

    override fun getRunResult(exitCode: Int): BuildFinishedStatus {
        if (exitCode == 0) {
            return BuildFinishedStatus.FINISHED_SUCCESS
        }

        val commandName = runnerParameters[CargoConstants.PARAM_COMMAND]
        val argumentsProvider = myArgumentsProviders[commandName]
        if (argumentsProvider == null) {
            val buildException = RunBuildException("Unable to construct arguments for cargo command $commandName")
            buildException.isLogStacktrace = false
            throw buildException
        }

        return if (argumentsProvider.shouldFailBuildIfCommandFailed()) {
            BuildFinishedStatus.FINISHED_FAILED
        } else {
            BuildFinishedStatus.FINISHED_SUCCESS
        }
    }

    override fun makeProgramCommandLine(): ProgramCommandLine {
        val parameters = runnerParameters

        val commandName = parameters[CargoConstants.PARAM_COMMAND]
        if (StringUtil.isEmpty(commandName)) {
            val buildException = RunBuildException("Cargo command name is empty")
            buildException.isLogStacktrace = false
            throw buildException
        }

        val argumentsProvider = myArgumentsProviders[commandName]
        if (argumentsProvider == null) {
            val buildException = RunBuildException("Unable to construct arguments for cargo command $commandName")
            buildException.isLogStacktrace = false
            throw buildException
        }

        val toolchainVersion = parameters[CargoConstants.PARAM_TOOLCHAIN]?.trim() ?: ""
        val (toolPath, providedArguments) = if (toolchainVersion.isNotEmpty()) {
            val rustupPath = getPath(CargoConstants.RUSTUP_CONFIG_NAME)
            rustupPath to argumentsProvider.getArguments(runnerContext).toMutableList().apply {
                addAll(0, arrayListOf("run", toolchainVersion, "cargo"))
            }
        } else {
            getPath(CargoConstants.CARGO_CONFIG_NAME) to argumentsProvider.getArguments(runnerContext)
        }

        val arguments = providedArguments.toMutableList()

        parameters[CargoConstants.PARAM_ADDITIONAL_ARGUMENTS]?.let {
            arguments.addAll(StringUtil.splitHonorQuotes(it))
        }

        runnerContext.configParameters[CargoConstants.CARGO_CONFIG_NAME]?.let {
            if (Version.valueOf(it) >= myCargoWithStdErrVersion) {
                return when (runnerContext.virtualContext.targetOSType) {
                    OSType.WINDOWS -> {
                        createProgramCommandline("cmd.exe", listOf("/c", toolPath) + arguments + listOf("2>&1"))
                    }
                    else -> {
                        val scriptlet = "$toolPath ${arguments.joinToString(" ")} 2>&1"
                        val toolName = File(toolPath).name
                        createProgramCommandline("/bin/sh", listOf("-c", scriptlet, toolName))
                    }
                }
            }
        }

        return createProgramCommandline(toolPath, arguments)
    }

    private fun getPath(toolName: String): String {
        try {
            return getToolPath(toolName)
        } catch (e: ToolCannotBeFoundException) {
            val buildException = RunBuildException(e)
            buildException.isLogStacktrace = false
            throw buildException
        }
    }

    override fun isCommandLineLoggingEnabled() = false

    override fun getListeners(): List<ProcessListener> {
        val loggerFactory = CargoLoggerFactory(logger)
        val command = runnerParameters[CargoConstants.PARAM_COMMAND]
        val blockName = if (command == CargoConstants.COMMAND_CUSTOM_CRATE) {
            val crate = runnerParameters[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
            "cargo $crate"
        } else "cargo $command"

        val listeners = mutableListOf(
                CargoLoggingListener(loggerFactory),
                BlockListener(blockName, logger)
        )

        if (command == CargoConstants.COMMAND_CLIPPY) {
            listeners.add(
                ClippyListener(inspectionReporter, clippyInspectionsParser)
            )
        }

        return listeners
    }
}
