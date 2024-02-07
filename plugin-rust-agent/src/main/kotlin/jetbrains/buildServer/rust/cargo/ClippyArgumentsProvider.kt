

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo clean command.
 */
class ClippyArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_CLIPPY)

        val manifestValue = parameters[CargoConstants.PARAM_CLIPPY_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue.trim())
        }

        return arguments
    }

    override fun shouldFailBuildIfCommandFailed(): Boolean = false
}