package jetbrains.buildServer.rust

import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.logging.BlockListener
import jetbrains.buildServer.rust.logging.ErrorDetectingListener
import java.util.concurrent.atomic.AtomicBoolean

class CargoInstallCrateService(private val crate: String) : BaseCargoBuildService() {
    override fun makeProgramCommandLine(): ProgramCommandLine {
        val (toolPath, toolArgs) = getToolPath()
        return createProgramCommandline(toolPath, toolArgs + listOf("install", "--locked", crate))
    }

    override val blockName: String = "install: $crate"
}

class RustupComponentAddBuildService(private val component: String) : BaseCargoBuildService() {
    override fun makeProgramCommandLine(): ProgramCommandLine =
        createProgramCommandline(getRustupPath(), listOf("component", "add", component))

    override val blockName: String = "add component: $component"
}

class RustupToolchainBuildService(private val action: String, private val toolchain: String) : BaseCargoBuildService() {
    private var foundVersion: String? = null

    val version: String
        get() = foundVersion ?: toolchain

    override fun makeProgramCommandLine(): ProgramCommandLine =
        createProgramCommandline(getRustupPath(), listOf("toolchain", action, toolchain))

    override fun getListeners(): List<ProcessListener> =
        super.getListeners() + listOf(object : ProcessListenerAdapter() {
            override fun onStandardOutput(text: String) = processOutput(text)
            override fun onErrorOutput(text: String) = processOutput(text)
        })

    override val blockName: String = "$action toolchain: $toolchain"

    private fun processOutput(text: String) {
        VERSION_PATTERN.matchEntire(text)?.let {
            foundVersion = it.groupValues.last()
        }
    }
}

private val VERSION_PATTERN = Regex("info: syncing channel updates for '([^']+)'")

class RustupTargetBuildService(private val target: String, private val toolchain: String?) : BaseCargoBuildService() {
    override fun makeProgramCommandLine(): ProgramCommandLine =
        createProgramCommandline(
            getRustupPath(),
            listOf("target", "add") + (toolchain?.let { listOf("--toolchain", toolchain) } ?: emptyList()) + listOf(target))

    override val blockName: String = "add target: $target"
}

abstract class BaseCargoBuildService : BuildServiceAdapter() {
    private val errorFlag = AtomicBoolean()

    fun hasErrors(): Boolean = errorFlag.get()

    protected fun getToolPath(): Pair<String, List<String>> {
        val toolchain = this.runnerParameters[CargoConstants.PARAM_TOOLCHAIN]
        val toolArgs = if (toolchain.isNullOrEmpty()) emptyList() else listOf("+" + toolchain.trim())
        return getCargoPath() to toolArgs
    }

    protected fun getCargoPath(): String = getPath(CargoConstants.CARGO_CONFIG_NAME)

    protected fun getRustupPath(): String = getPath(CargoConstants.RUSTUP_CONFIG_NAME)

    private fun getPath(toolName: String): String =
        try {
            getToolPath(toolName)
        } catch (e: ToolCannotBeFoundException) {
            val buildException = RunBuildException(e)
            buildException.isLogStacktrace = false
            throw buildException
        }

    override fun isCommandLineLoggingEnabled() = false

    override fun getListeners(): List<ProcessListener> =
        listOf(BlockListener(blockName, logger), ErrorDetectingListener(logger, errorFlag))

    protected abstract val blockName: String
}
