

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo package command.
 */
class PackageArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_PACKAGE)

        val noVerifyValue = parameters[CargoConstants.PARAM_PACKAGE_NO_VERIFY]
        if ("true".equals(noVerifyValue, ignoreCase = true)) {
            arguments.add("--no-verify")
        }

        val noMetadataValue = parameters[CargoConstants.PARAM_PACKAGE_NO_METADATA]
        if ("true".equals(noMetadataValue, ignoreCase = true)) {
            arguments.add("--no-metadata")
        }

        val manifestValue = parameters[CargoConstants.PARAM_PACKAGE_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue.trim())
        }

        addCommonArguments(parameters, arguments)

        return arguments
    }
}