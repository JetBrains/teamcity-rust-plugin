package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.lang.IllegalStateException
import java.util.*

/**
 * Provides arguments to custom crate command.
 */
class CustomCrateArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val command = runnerContext.runnerParameters[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
            ?: throw IllegalStateException("Custom crate name should be provided")
        return listOf(command)
    }

    override fun shouldFailBuildIfCommandFailed(): Boolean = true
}
