

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo build command.
 */
class BuildCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_BUILD

    override val editPage: String
        get() = "editBuildParameters.jsp"

    override val viewPage: String
        get() = "viewBuildParameters.jsp"
}