

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo bench command.
 */
class CustomCrateCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_CUSTOM_CRATE

    override val editPage: String
        get() = "editCustomCrateParameters.jsp"

    override val viewPage: String
        get() = "viewCustomCrateParameters.jsp"
}