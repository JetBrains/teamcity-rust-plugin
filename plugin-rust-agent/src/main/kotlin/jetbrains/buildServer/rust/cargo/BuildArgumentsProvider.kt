

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo build command.
 */
class BuildArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_BUILD)

        val packageValue = parameters[CargoConstants.PARAM_BUILD_PACKAGE]
        if (!packageValue.isNullOrBlank()) {
            arguments.add("--package")
            arguments.add(packageValue.trim())
        }

        val parallelJobsValue = parameters[CargoConstants.PARAM_BUILD_PARALLEL]
        if (!parallelJobsValue.isNullOrBlank()) {
            arguments.add("--jobs")
            arguments.add(parallelJobsValue.trim())
        }

        val typeValue = parameters[CargoConstants.PARAM_BUILD_TYPE]
        if (!typeValue.isNullOrBlank()) {
            arguments.add(typeValue.trim())
            val typeNameValue = parameters[CargoConstants.PARAM_BUILD_TYPE_NAME]
            if (!typeNameValue.isNullOrBlank()) {
                arguments.add(typeNameValue.trim())
            }
        }

        val releaseValue = parameters[CargoConstants.PARAM_BUILD_RELEASE]
        if ("true".equals(releaseValue, ignoreCase = true)) {
            arguments.add("--release")
        }

        val featuresValue = parameters[CargoConstants.PARAM_BUILD_FEATURES]
        if (!featuresValue.isNullOrBlank()) {
            arguments.add("--features")
            arguments.add(featuresValue.trim())
        }

        val noDefaultFeaturesValue = parameters[CargoConstants.PARAM_BUILD_NO_DEFAULT_FEATURES]
        if ("true".equals(noDefaultFeaturesValue, ignoreCase = true)) {
            arguments.add("--no-default-features")
        }

        val targetValue = parameters[CargoConstants.PARAM_BUILD_TARGET]
        if (!targetValue.isNullOrBlank()) {
            arguments.add("--target")
            arguments.add(targetValue.trim())
        }

        val manifestValue = parameters[CargoConstants.PARAM_BUILD_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue.trim())
        }

        addCommonArguments(parameters, arguments)

        return arguments
    }
}