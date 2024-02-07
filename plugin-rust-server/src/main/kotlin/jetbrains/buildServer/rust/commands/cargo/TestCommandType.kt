

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo test command.
 */
class TestCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_TEST

    override val editPage: String
        get() = "editTestParameters.jsp"

    override val viewPage: String
        get() = "viewTestParameters.jsp"
}