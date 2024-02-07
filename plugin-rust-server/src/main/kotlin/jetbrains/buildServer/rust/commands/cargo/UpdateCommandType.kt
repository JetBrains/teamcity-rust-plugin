

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo update command.
 */
class UpdateCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_UPDATE

    override val editPage: String
        get() = "editUpdateParameters.jsp"

    override val viewPage: String
        get() = "viewUpdateParameters.jsp"
}