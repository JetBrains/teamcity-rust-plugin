

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo doc command.
 */
class DocCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_DOC

    override val editPage: String
        get() = "editDocParameters.jsp"

    override val viewPage: String
        get() = "viewDocParameters.jsp"
}