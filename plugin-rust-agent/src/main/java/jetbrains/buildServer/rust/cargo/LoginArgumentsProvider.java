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
 * Provides arguments to cargo login command.
 */
public class LoginArgumentsProvider implements ArgumentsProvider {

    @NotNull
    @Override
    public List<String> getArguments(@NotNull final Map<String, String> parameters) {
        final List<String> arguments = new ArrayList<String>();
        arguments.add(CargoConstants.COMMAND_LOGIN);

        final String hostValue = parameters.get(CargoConstants.PARAM_LOGIN_HOST);
        if (!StringUtil.isEmptyOrSpaces(hostValue)) {
            arguments.add("--host");
            arguments.add(hostValue.trim());
        }

        final String verbosityValue = parameters.get(CargoConstants.PARAM_VERBOSITY);
        if (!StringUtil.isEmptyOrSpaces(verbosityValue)) {
            arguments.add(verbosityValue.trim());
        }

        final String tokenValue = parameters.get(CargoConstants.PARAM_LOGIN_TOKEN);
        if (!StringUtil.isEmptyOrSpaces(tokenValue)) {
            arguments.add(tokenValue.trim());
        }

        return arguments;
    }
}
