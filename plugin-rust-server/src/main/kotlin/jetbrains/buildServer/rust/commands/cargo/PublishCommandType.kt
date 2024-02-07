

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo publish command.
 */
class PublishCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_PUBLISH

    override val editPage: String
        get() = "editPublishParameters.jsp"

    override val viewPage: String
        get() = "viewPublishParameters.jsp"
}