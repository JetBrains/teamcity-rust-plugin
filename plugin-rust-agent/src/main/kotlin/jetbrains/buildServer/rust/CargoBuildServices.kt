package jetbrains.buildServer.rust

import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.logging.BlockListener

class CargoInstallCrateService(private val crate: String) : BaseCargoBuildService() {
    override fun makeProgramCommandLine(): ProgramCommandLine {
        val (toolPath, toolArgs) = getToolPath()
        return createProgramCommandline(toolPath, toolArgs + listOf("install", "--locked", crate))
    }
}

class RustupComponentAddBuildService(private val component: String) : BaseCargoBuildService() {
    override fun makeProgramCommandLine(): ProgramCommandLine =
        createProgramCommandline(getRustupPath(), arrayListOf("component", "add", component))
}

class RustupToolchainBuildService(private val action: String) : BaseCargoBuildService() {
    val errors = arrayListOf<String>()

    private var foundVersion: String? = null

    val version: String
        get() = foundVersion ?: runnerParameters[CargoConstants.PARAM_TOOLCHAIN]!!

    override fun makeProgramCommandLine(): ProgramCommandLine {
        val toolchainVersion = runnerParameters[CargoConstants.PARAM_TOOLCHAIN]!!.trim()
        return createProgramCommandline(getRustupPath(), arrayListOf("toolchain", action, toolchainVersion))
    }

    override fun getListeners(): List<ProcessListener> {
        val blockName = "$action toolchain: ${runnerParameters[CargoConstants.PARAM_TOOLCHAIN]}"
        return listOf(BlockListener(blockName, logger), object : ProcessListenerAdapter() {
            override fun onStandardOutput(text: String) = processOutput(text)
            override fun onErrorOutput(text: String) = processOutput(text)
        })
    }

    fun processOutput(text: String) {
        if (text.startsWith("error:")) {
            errors.add(text)
        }

        toolchainVersion.matchEntire(text)?.let {
            foundVersion = it.groupValues.last()
        }

        logger.message(text)
    }
}

private val toolchainVersion = Regex("info: syncing channel updates for '([^']+)'")

abstract class BaseCargoBuildService : BuildServiceAdapter() {
    protected fun getToolPath(): Pair<String, List<String>> {
        val toolchain = this.runnerParameters[CargoConstants.PARAM_TOOLCHAIN]
        val toolArgs = if (toolchain.isNullOrEmpty()) emptyList() else listOf("+" + toolchain.trim())
        return getCargoPath() to toolArgs
    }

    protected fun getCargoPath(): String = getPath(CargoConstants.CARGO_CONFIG_NAME)
    protected fun getRustupPath(): String = getPath(CargoConstants.RUSTUP_CONFIG_NAME)

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

    override fun getListeners(): List<ProcessListener> = emptyList()
}
