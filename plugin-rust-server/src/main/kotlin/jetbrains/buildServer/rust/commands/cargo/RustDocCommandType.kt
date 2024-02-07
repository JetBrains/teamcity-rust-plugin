

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo rustdoc command.
 */
class RustDocCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_RUSTDOC

    override val editPage: String
        get() = "editRustDocParameters.jsp"

    override val viewPage: String
        get() = "viewRustDocParameters.jsp"
}