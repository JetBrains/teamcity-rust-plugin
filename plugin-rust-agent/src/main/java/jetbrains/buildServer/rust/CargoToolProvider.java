/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust;

import jetbrains.buildServer.agent.*;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Determines cargo location.
 */
public class CargoToolProvider implements ToolProvider {

    private static final Pattern TOOL_PATTERN = Pattern.compile("^.*" + CargoConstants.RUNNER_TYPE + "(\\.(exe))?$");

    public CargoToolProvider(@NotNull final ToolProvidersRegistry toolProvidersRegistry) {
        toolProvidersRegistry.registerToolProvider(this);
    }

    @Override
    public boolean supports(@NotNull String toolName) {
        return CargoConstants.RUNNER_TYPE.equalsIgnoreCase(toolName);
    }

    @NotNull
    @Override
    public String getPath(@NotNull String toolName) throws ToolCannotBeFoundException {
        final String pathVariable = System.getenv("PATH");
        final List<String> paths = StringUtil.splitHonorQuotes(pathVariable, File.pathSeparatorChar);

        final String toolPath = FileUtils.findToolPath(paths, TOOL_PATTERN);
        if (StringUtil.isEmpty(toolPath)) {
            String message = String.format("Unable to locate tool %s in system. Please make sure to add it in the PATH variable", toolName);
            throw new ToolCannotBeFoundException(message);
        }

        return toolPath;
    }

    @NotNull
    @Override
    public String getPath(@NotNull String toolName,
                          @NotNull AgentRunningBuild build,
                          @NotNull BuildRunnerContext runner) throws ToolCannotBeFoundException {
        return getPath(toolName);
    }
}
