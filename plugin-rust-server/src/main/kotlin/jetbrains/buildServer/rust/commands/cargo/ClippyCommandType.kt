

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo clean command.
 */
class ClippyCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_CLIPPY

    override val editPage: String
        get() = "editClippyParameters.jsp"

    override val viewPage: String
        get() = "viewClippyParameters.jsp"
}