

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for `cargo check` command.
 */
class CheckCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_CHECK

    override val editPage: String
        get() = "editCheckParameters.jsp"

    override val viewPage: String
        get() = "viewCheckParameters.jsp"
}