/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

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
