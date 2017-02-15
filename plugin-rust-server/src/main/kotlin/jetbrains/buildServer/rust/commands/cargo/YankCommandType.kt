/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.commands.cargo

import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.commands.CommandType

/**
 * Provides parameters for cargo yank command.
 */
class YankCommandType : CommandType {
    override val name: String
        get() = CargoConstants.COMMAND_YANK

    override val editPage: String
        get() = "editYankParameters.jsp"

    override val viewPage: String
        get() = "viewYankParameters.jsp"
}
