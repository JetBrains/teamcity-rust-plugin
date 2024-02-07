

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo package command.
 */
class PackageCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_PACKAGE

    override val editPage: String
        get() = "editPackageParameters.jsp"

    override val viewPage: String
        get() = "viewPackageParameters.jsp"
}