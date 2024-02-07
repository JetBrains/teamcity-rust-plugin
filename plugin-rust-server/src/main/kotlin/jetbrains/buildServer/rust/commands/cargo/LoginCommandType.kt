

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo login command.
 */
class LoginCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_LOGIN

    override val editPage: String
        get() = "editLoginParameters.jsp"

    override val viewPage: String
        get() = "viewLoginParameters.jsp"
}