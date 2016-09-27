/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.cargo.*
import jetbrains.buildServer.rust.logging.CargoLoggerFactory
import jetbrains.buildServer.rust.logging.CargoLoggingListener
import jetbrains.buildServer.util.StringUtil

import java.util.Collections
import java.util.HashMap

/**
 * Cargo runner service.
 */
class CargoRunnerBuildService : BuildServiceAdapter() {
    private val myArgumentsProviders: MutableMap<String, ArgumentsProvider>

    init {
        myArgumentsProviders = HashMap<String, ArgumentsProvider>()
        myArgumentsProviders.put(CargoConstants.COMMAND_BENCH, BenchArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_BUILD, BuildArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_CLEAN, CleanArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_DOC, DocArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_LOGIN, LoginArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_PACKAGE, PackageArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_PUBLISH, PublishArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_RUN, RunArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_RUSTC, RustcArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_RUSTDOC, RustDocArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_TEST, TestArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_UPDATE, UpdateArgumentsProvider())
        myArgumentsProviders.put(CargoConstants.COMMAND_YANK, YankArgumentsProvider())
    }

    @Throws(RunBuildException::class)
    override fun makeProgramCommandLine(): ProgramCommandLine {
        val parameters = runnerParameters

        val commandName = parameters[CargoConstants.PARAM_COMMAND]
        if (StringUtil.isEmpty(commandName)) {
            val buildException = RunBuildException("Cargo command name is empty")
            buildException.isLogStacktrace = false
            throw buildException
        }

        val argumentsProvider = myArgumentsProviders.get(commandName)
        if (argumentsProvider == null) {
            val buildException = RunBuildException("Unable to construct arguments for cargo command " + commandName)
            buildException.isLogStacktrace = false
            throw buildException
        }

        val toolPath: String
        val arguments = argumentsProvider.getArguments(parameters)
        try {
            toolPath = getToolPath(CargoConstants.RUNNER_TYPE)
        } catch (e: ToolCannotBeFoundException) {
            val buildException = RunBuildException(e)
            buildException.isLogStacktrace = false
            throw buildException
        }

        return createProgramCommandline(toolPath, arguments)
    }

    override fun getListeners(): List<ProcessListener> {
        val loggerFactory = CargoLoggerFactory(logger)
        return listOf<ProcessListener>(CargoLoggingListener(loggerFactory))
    }
}
