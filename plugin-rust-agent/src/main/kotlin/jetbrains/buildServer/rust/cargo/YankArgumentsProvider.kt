

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo yank command.
 */
class YankArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_YANK)

        val versionValue = parameters[CargoConstants.PARAM_YANK_VERSION]
        if (!versionValue.isNullOrBlank()) {
            arguments.add("--vers")
            arguments.add(versionValue.trim())
        }

        val undoValue = parameters[CargoConstants.PARAM_YANK_UNDO]
        if ("true".equals(undoValue, ignoreCase = true)) {
            arguments.add("--undo")
        }

        val indexValue = parameters[CargoConstants.PARAM_YANK_INDEX]
        if (!indexValue.isNullOrBlank()) {
            arguments.add("--index")
            arguments.add(indexValue.trim())
        }

        val tokenValue = parameters[CargoConstants.PARAM_YANK_TOKEN_SECURE] ?: parameters[CargoConstants.PARAM_YANK_TOKEN]
        if (!tokenValue.isNullOrBlank()) {
            arguments.add("--token")
            arguments.add(tokenValue.trim())
        }

        addCommonArguments(parameters, arguments)

        val crateValue = parameters[CargoConstants.PARAM_YANK_CRATE]
        if (!crateValue.isNullOrBlank()) {
            arguments.add(crateValue.trim())
        }

        return arguments
    }
}