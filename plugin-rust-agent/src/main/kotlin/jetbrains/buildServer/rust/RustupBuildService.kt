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
class RustupBuildService(private val action: String) : BuildServiceAdapter() {

    val errors = arrayListOf<String>()
    var foundVersion: String? = null

    val version: String
        get() = foundVersion ?: runnerParameters[CargoConstants.PARAM_TOOLCHAIN]!!

    override fun makeProgramCommandLine(): ProgramCommandLine {
        val toolchainVersion = runnerParameters[CargoConstants.PARAM_TOOLCHAIN]!!.trim()
        val rustupPath = getPath(CargoConstants.RUSTUP_CONFIG_NAME)

        return createProgramCommandline(rustupPath, arrayListOf("toolchain", action, toolchainVersion))
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

    override fun getListeners(): MutableList<ProcessListener> {
        return arrayListOf<ProcessListener>().apply {
            val blockName = "$action toolchain: ${runnerParameters[CargoConstants.PARAM_TOOLCHAIN]}"
            this.add(BlockListener(blockName, logger))
            this.add(object : ProcessListenerAdapter() {
                override fun onStandardOutput(text: String) {
                    processOutput(text)
                }

                override fun onErrorOutput(text: String) {
                    processOutput(text)
                }
            })
        }
    }

    fun processOutput(text: String) {
        if (text.startsWith("error:")) {
            errors.add(text)
        }

        toolchainVersion.matchEntire(text)?.let {
            foundVersion = it.groupValues.last()
        }

        logger.message(text)
    }

    companion object {
        val toolchainVersion = Regex("info: syncing channel updates for '([^']+)'")
    }
}
