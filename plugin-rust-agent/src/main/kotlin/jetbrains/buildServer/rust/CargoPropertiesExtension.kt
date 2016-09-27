/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.agent.*
import jetbrains.buildServer.util.EventDispatcher

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

        LOG.info("Locating .NET CLI tools")
        try {
            toolPath = myToolProvider.getPath(CargoConstants.RUNNER_TYPE)
        } catch (e: ToolCannotBeFoundException) {
            LOG.debug(e)
            return
        }

        LOG.info("Found cargo at $toolPath")
        config.addConfigurationParameter(CargoConstants.CONFIG_PATH, toolPath)
    }

    companion object {
        private val LOG = Logger.getInstance(CargoPropertiesExtension::class.java.name)
    }
}