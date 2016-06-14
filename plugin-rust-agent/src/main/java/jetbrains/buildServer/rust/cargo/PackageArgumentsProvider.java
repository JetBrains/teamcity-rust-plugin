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
 * Provides arguments to cargo package command.
 */
public class PackageArgumentsProvider implements ArgumentsProvider {

    @NotNull
    @Override
    public List<String> getArguments(@NotNull final Map<String, String> parameters) {
        final List<String> arguments = new ArrayList<String>();
        arguments.add(CargoConstants.COMMAND_PACKAGE);

        final String noVerifyValue = parameters.get(CargoConstants.PARAM_PACKAGE_NO_VERIFY);
        if ("true".equalsIgnoreCase(noVerifyValue)) {
            arguments.add("--no-verify");
        }

        final String noMetadataValue = parameters.get(CargoConstants.PARAM_PACKAGE_NO_METADATA);
        if ("true".equalsIgnoreCase(noMetadataValue)) {
            arguments.add("--no-metadata");
        }

        final String manifestValue = parameters.get(CargoConstants.PARAM_PACKAGE_MANIFEST);
        if (!StringUtil.isEmptyOrSpaces(manifestValue)) {
            arguments.add("--manifest-path");
            arguments.add(manifestValue.trim());
        }

        final String verbosityValue = parameters.get(CargoConstants.PARAM_VERBOSITY);
        if (!StringUtil.isEmptyOrSpaces(verbosityValue)) {
            arguments.add(verbosityValue.trim());
        }

        return arguments;
    }
}
