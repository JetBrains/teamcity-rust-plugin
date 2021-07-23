/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.ToolCannotBeFoundException
import jetbrains.buildServer.agent.runner.BuildServiceAdapter
import jetbrains.buildServer.agent.runner.ProcessListener
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.rust.logging.BlockListener

/**
 * Rustup runner service.
 */
class RustupComponentAddBuildService(private val component: String) : BuildServiceAdapter() {

    val errors = arrayListOf<String>()
    override fun makeProgramCommandLine(): ProgramCommandLine {
        val rustupPath = getRustUpPath()
        return createProgramCommandline(rustupPath, arrayListOf("component", "add", component))
    }

    private fun getRustUpPath(): String {
        try {
            return getToolPath(CargoConstants.RUSTUP_CONFIG_NAME)
        } catch (e: ToolCannotBeFoundException) {
            val buildException = RunBuildException(e)
            buildException.isLogStacktrace = false
            throw buildException
        }
    }

    override fun isCommandLineLoggingEnabled() = false

    override fun getListeners(): List<ProcessListener> = emptyList()
}
