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
import java.util.regex.Pattern

/**
 * Provides a cargo tool path.
 */
class CargoPropertiesExtension(events: EventDispatcher<AgentLifeCycleListener>,
                               private val myToolProvider: CargoToolProvider) : AgentLifeCycleAdapter() {

    init {
        events.addListener(this)
    }

    override fun beforeAgentConfigurationLoaded(agent: BuildAgent) {
        val config = agent.configuration
        val toolPath: String
        val version: String

        LOG.info("Locating ${CargoConstants.CONFIG_NAME} tool")
        try {
            toolPath = myToolProvider.getPath(CargoConstants.RUNNER_TYPE)
            val commandLine = getVersionCommandLine(toolPath)
            val result = SimpleCommandLineProcessRunner.runCommand(commandLine, byteArrayOf())
            val matcher = VERSION_PATTERN.matcher(result.stdout)
            version = if (matcher.find()) matcher.group(1) else result.stdout
        } catch (e: ToolCannotBeFoundException) {
            LOG.debug(e)
            return
        }

        LOG.info("Found ${CargoConstants.CONFIG_NAME} at $toolPath")
        config.addConfigurationParameter(CargoConstants.CONFIG_NAME, version)
        config.addConfigurationParameter(CargoConstants.CONFIG_PATH, toolPath)
    }

    private fun getVersionCommandLine(toolPath: String): GeneralCommandLine {
        val commandLine = GeneralCommandLine()
        commandLine.exePath = toolPath
        commandLine.addParameter("--version")
        return commandLine
    }

    companion object {
        private val LOG = Logger.getInstance(CargoPropertiesExtension::class.java.name)
        private val VERSION_PATTERN = Pattern.compile("^cargo\\s([^\\s]+)")
    }
}