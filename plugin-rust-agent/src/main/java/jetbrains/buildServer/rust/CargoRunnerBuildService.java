/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust;

import jetbrains.buildServer.RunBuildException;
import jetbrains.buildServer.agent.ToolCannotBeFoundException;
import jetbrains.buildServer.agent.runner.BuildServiceAdapter;
import jetbrains.buildServer.agent.runner.ProgramCommandLine;
import jetbrains.buildServer.rust.cargo.BuildArgumentsProvider;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cargo runner service.
 */
public class CargoRunnerBuildService extends BuildServiceAdapter {

    private final Map<String, ArgumentsProvider> myArgumentsProviders;

    public CargoRunnerBuildService() {
        myArgumentsProviders = new HashMap<String, ArgumentsProvider>();
        myArgumentsProviders.put(CargoConstants.COMMAND_BUILD, new BuildArgumentsProvider());
    }

    @NotNull
    @Override
    public ProgramCommandLine makeProgramCommandLine() throws RunBuildException {
        final Map<String, String> parameters = getRunnerParameters();

        final String commandName = parameters.get(CargoConstants.PARAM_COMMAND);
        if (StringUtil.isEmpty(commandName)) {
            RunBuildException buildException = new RunBuildException("Cargo command name is empty");
            buildException.setLogStacktrace(false);
            throw buildException;
        }

        final ArgumentsProvider argumentsProvider = myArgumentsProviders.get(commandName);
        if (argumentsProvider == null) {
            RunBuildException buildException = new RunBuildException("Unable to construct arguments for cargo command " + commandName);
            buildException.setLogStacktrace(false);
            throw buildException;
        }

        final String toolPath;
        final List<String> arguments = argumentsProvider.getArguments(parameters);
        try {
            toolPath = getToolPath(CargoConstants.RUNNER_TYPE);
        } catch (ToolCannotBeFoundException e){
            RunBuildException buildException = new RunBuildException(e);
            buildException.setLogStacktrace(false);
            throw buildException;
        }

        return createProgramCommandline(toolPath, arguments);
    }
}
