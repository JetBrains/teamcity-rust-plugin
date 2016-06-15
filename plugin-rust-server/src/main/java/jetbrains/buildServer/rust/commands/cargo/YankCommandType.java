/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.commands.cargo;

import jetbrains.buildServer.rust.CargoConstants;
import jetbrains.buildServer.rust.commands.CommandType;
import org.jetbrains.annotations.NotNull;

/**
 * Provides parameters for cargo yank command.
 */
public class YankCommandType implements CommandType {
    @NotNull
    @Override
    public String getName() {
        return CargoConstants.COMMAND_YANK;
    }

    @NotNull
    @Override
    public String getEditPage() {
        return "editYankParameters.jsp";
    }

    @NotNull
    @Override
    public String getViewPage() {
        return "viewYankParameters.jsp";
    }
}
