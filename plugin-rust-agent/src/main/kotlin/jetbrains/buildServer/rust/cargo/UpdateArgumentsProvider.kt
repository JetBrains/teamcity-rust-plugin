

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo update command.
 */
class UpdateArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_UPDATE)

        val packageValue = parameters[CargoConstants.PARAM_UPDATE_PACKAGE]
        if (!packageValue.isNullOrBlank()) {
            arguments.add("--package")
            arguments.add(packageValue.trim())
        }

        val preciseValue = parameters[CargoConstants.PARAM_UPDATE_PRECISE]
        if (!preciseValue.isNullOrBlank()) {
            arguments.add("--precise")
            arguments.add(preciseValue.trim())
        }

        val aggressiveValue = parameters[CargoConstants.PARAM_UPDATE_AGGRESSIVE]
        if ("true".equals(aggressiveValue, ignoreCase = true)) {
            arguments.add("--aggressive")
        }

        val manifestValue = parameters[CargoConstants.PARAM_UPDATE_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue.trim())
        }

        addCommonArguments(parameters, arguments)

        return arguments
    }
}