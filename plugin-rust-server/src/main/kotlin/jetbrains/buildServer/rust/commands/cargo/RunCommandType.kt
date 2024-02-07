

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo run command.
 */
class RunCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_RUN

    override val editPage: String
        get() = "editRunParameters.jsp"

    override val viewPage: String
        get() = "viewRunParameters.jsp"
}