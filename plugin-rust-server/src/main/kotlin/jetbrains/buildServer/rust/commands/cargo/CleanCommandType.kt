

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo clean command.
 */
class CleanCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_CLEAN

    override val editPage: String
        get() = "editCleanParameters.jsp"

    override val viewPage: String
        get() = "viewCleanParameters.jsp"
}