

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo bench command.
 */
class BenchCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_BENCH

    override val editPage: String
        get() = "editBenchParameters.jsp"

    override val viewPage: String
        get() = "viewBenchParameters.jsp"
}