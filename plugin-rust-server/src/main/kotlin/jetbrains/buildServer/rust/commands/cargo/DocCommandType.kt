/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

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
