

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo rustc command.
 */
class RustcCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_RUSTC

    override val editPage: String
        get() = "editRustcParameters.jsp"

    override val viewPage: String
        get() = "viewRustcParameters.jsp"
}