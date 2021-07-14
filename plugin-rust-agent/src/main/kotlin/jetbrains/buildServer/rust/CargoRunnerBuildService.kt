/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import com.github.zafarkhaja.semver.Version
import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.BuildRunnerContextEx
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.inspections.InspectionReporter
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.cargo.*
import jetbrains.buildServer.rust.inspections.ClippyInspectionsParser
import jetbrains.buildServer.rust.inspections.ClippyListener
import jetbrains.buildServer.rust.logging.BlockListener
import jetbrains.buildServer.rust.logging.CargoLoggerFactory
import jetbrains.buildServer.rust.logging.CargoLoggingListener
import jetbrains.buildServer.util.OSType
import jetbrains.buildServer.util.StringUtil

/**
 * Cargo runner service.
 */
class CargoRunnerBuildService(
        private val runnerContext: BuildRunnerContextEx,
        private val inspectionReporter: InspectionReporter,
        private val clippyInspectionsParser: ClippyInspectionsParser
) : BuildServiceAdapter() {

    private val myCargoWithStdErrVersion = Version.forIntegers(0, 13)
    private val myArgumentsProviders = mapOf(
            CargoConstants.COMMAND_BENCH to BenchArgumentsProvider(),
            CargoConstants.COMMAND_BUILD to BuildArgumentsProvider(),
            CargoConstants.COMMAND_CLEAN to CleanArgumentsProvider(),
            CargoConstants.COMMAND_CLIPPY to ClippyArgumentsProvider(),
            CargoConstants.COMMAND_DOC to DocArgumentsProvider(),
            CargoConstants.COMMAND_LOGIN to LoginArgumentsProvider(),
            CargoConstants.COMMAND_PACKAGE to PackageArgumentsProvider(),
            CargoConstants.COMMAND_PUBLISH to PublishArgumentsProvider(),
            CargoConstants.COMMAND_RUN to RunArgumentsProvider(),
            CargoConstants.COMMAND_RUSTC to RustcArgumentsProvider(),
            CargoConstants.COMMAND_RUSTDOC to RustDocArgumentsProvider(),
            CargoConstants.COMMAND_TEST to TestArgumentsProvider(),
            CargoConstants.COMMAND_UPDATE to UpdateArgumentsProvider(),
            CargoConstants.COMMAND_YANK to YankArgumentsProvider()
    )

    override fun makeProgramCommandLine(): ProgramCommandLine {
        val parameters = runnerParameters

        val commandName = parameters[CargoConstants.PARAM_COMMAND]
        if (StringUtil.isEmpty(commandName)) {
            val buildException = RunBuildException("Cargo command name is empty")
            buildException.isLogStacktrace = false
            throw buildException
        }

        val argumentsProvider = myArgumentsProviders[commandName]
        if (argumentsProvider == null) {
            val buildException = RunBuildException("Unable to construct arguments for cargo command $commandName")
            buildException.isLogStacktrace = false
            throw buildException
        }

        val toolchainVersion = parameters[CargoConstants.PARAM_TOOLCHAIN]?.trim() ?: ""
        val (toolPath, arguments) = if (toolchainVersion.isNotEmpty()) {
            val rustupPath = getPath(CargoConstants.RUSTUP_CONFIG_NAME)
            rustupPath to argumentsProvider.getArguments(runnerContext).toMutableList().apply {
                addAll(0, arrayListOf("run", toolchainVersion, "cargo"))
            }
        } else {
            getPath(CargoConstants.CARGO_CONFIG_NAME) to argumentsProvider.getArguments(runnerContext)
        }

        runnerContext.configParameters[CargoConstants.CARGO_CONFIG_NAME]?.let {
            if (Version.valueOf(it).greaterThanOrEqualTo(myCargoWithStdErrVersion)) {
                return when (runnerContext.virtualContext.targetOSType) {
                    OSType.WINDOWS -> {
                        createProgramCommandline("cmd.exe", arrayListOf("/c", "2>&1", toolPath).apply {
                            addAll(arguments)
                        })
                    }
                    OSType.UNIX -> {
                        createProgramCommandline("sh", arrayListOf("-c", "$toolPath ${arguments.joinToString(" ")} 2>&1"))
                    }
                    else -> {
                        createProgramCommandline("bash", arrayListOf("-c", "$toolPath ${arguments.joinToString(" ")} 2>&1"))
                    }
                }
            }
        }

        return createProgramCommandline(toolPath, arguments)
    }

    private fun getPath(toolName: String): String {
        try {
            return getToolPath(toolName)
        } catch (e: ToolCannotBeFoundException) {
            val buildException = RunBuildException(e)
            buildException.isLogStacktrace = false
            throw buildException
        }
    }

    override fun isCommandLineLoggingEnabled() = false

    override fun getListeners(): List<ProcessListener> {
        val loggerFactory = CargoLoggerFactory(logger)
        val command = runnerParameters[CargoConstants.PARAM_COMMAND]
        val blockName = "cargo $command"

        val listeners = mutableListOf(
                CargoLoggingListener(loggerFactory),
                BlockListener(blockName, logger)
        )

        if (command == CargoConstants.COMMAND_CLIPPY) {
            listeners.add(
                ClippyListener(inspectionReporter, clippyInspectionsParser)
            )
        }

        return listeners
    }
}
