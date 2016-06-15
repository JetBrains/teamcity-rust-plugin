/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo;

import jetbrains.buildServer.rust.ArgumentsProvider;
import jetbrains.buildServer.rust.CargoConstants;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Provides arguments to cargo yank command.
 */
public class YankArgumentsProvider implements ArgumentsProvider {

    @NotNull
    @Override
    public List<String> getArguments(@NotNull final Map<String, String> parameters) {
        final List<String> arguments = new ArrayList<String>();
        arguments.add(CargoConstants.COMMAND_YANK);

        final String versionValue = parameters.get(CargoConstants.PARAM_YANK_VERSION);
        if (!StringUtil.isEmptyOrSpaces(versionValue)) {
            arguments.add("--vers");
            arguments.add(versionValue.trim());
        }

        final String undoValue = parameters.get(CargoConstants.PARAM_YANK_UNDO);
        if ("true".equalsIgnoreCase(undoValue)) {
            arguments.add("--undo");
        }

        final String indexValue = parameters.get(CargoConstants.PARAM_YANK_INDEX);
        if (!StringUtil.isEmptyOrSpaces(indexValue)) {
            arguments.add("--index");
            arguments.add(indexValue.trim());
        }

        final String tokenValue = parameters.get(CargoConstants.PARAM_YANK_TOKEN);
        if (!StringUtil.isEmptyOrSpaces(tokenValue)) {
            arguments.add("--token");
            arguments.add(tokenValue.trim());
        }

        final String verbosityValue = parameters.get(CargoConstants.PARAM_VERBOSITY);
        if (!StringUtil.isEmptyOrSpaces(verbosityValue)) {
            arguments.add(verbosityValue.trim());
        }

        final String crateValue = parameters.get(CargoConstants.PARAM_YANK_CRATE);
        if (!StringUtil.isEmptyOrSpaces(crateValue)) {
            arguments.add(crateValue.trim());
        }

        return arguments;
    }
}
