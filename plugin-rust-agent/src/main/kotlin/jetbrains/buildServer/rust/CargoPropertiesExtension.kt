/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.SimpleCommandLineProcessRunner
import jetbrains.buildServer.agent.*
import jetbrains.buildServer.util.EventDispatcher

/**
 * Provides a cargo tool path.
 */
class CargoPropertiesExtension(events: EventDispatcher<AgentLifeCycleListener>,
                               private val myToolProvider: CargoToolProvider) : AgentLifeCycleAdapter() {

    private val LOG = Logger.getInstance(CargoPropertiesExtension::class.java.name)
    private val VERSION_PATTERN = Regex("^(cargo|rustc)\\s([^\\s]+)")

    init {
        events.addListener(this)
    }

    override fun beforeAgentConfigurationLoaded(agent: BuildAgent) {
        val config = agent.configuration

        getToolInfo(CargoConstants.CARGO_CONFIG_NAME)?.let {
            config.addConfigurationParameter(CargoConstants.CARGO_CONFIG_PATH, it.first)
            config.addConfigurationParameter(CargoConstants.CARGO_CONFIG_NAME, it.second)
        }

        getToolInfo(CargoConstants.RUSTC_CONFIG_NAME)?.let {
            config.addConfigurationParameter(CargoConstants.RUSTC_CONFIG_PATH, it.first)
            config.addConfigurationParameter(CargoConstants.RUSTC_CONFIG_NAME, it.second)
        }
    }

    private fun getToolInfo(toolName: String): Pair<String, String>? {
        LOG.info("Locating $toolName tool")
        try {
            val toolPath = myToolProvider.getPath(toolName)
            val commandLine = getVersionCommandLine(toolPath)
            val result = SimpleCommandLineProcessRunner.runCommand(commandLine, byteArrayOf())
            val matchResult = VERSION_PATTERN.find(result.stdout)
            val version = matchResult?.groups?.get(2)?.value ?: result.stdout
            LOG.info("Found $toolName at $toolPath")
            return toolPath to version
        } catch (e: ToolCannotBeFoundException) {
            LOG.debug(e)
            return null
        }
    }

    private fun getVersionCommandLine(toolPath: String): GeneralCommandLine {
        val commandLine = GeneralCommandLine()
        commandLine.exePath = toolPath
        commandLine.addParameter("--version")
        return commandLine
    }
}