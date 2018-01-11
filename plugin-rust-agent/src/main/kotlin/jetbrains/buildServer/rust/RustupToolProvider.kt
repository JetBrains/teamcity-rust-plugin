/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.AgentLifeCycleListener
import jetbrains.buildServer.agent.ToolProvidersRegistry
import jetbrains.buildServer.util.EventDispatcher
import java.io.File

/**
 * Determines rustup location.
 */
class RustupToolProvider(toolsRegistry: ToolProvidersRegistry,
                        events: EventDispatcher<AgentLifeCycleListener>)
    : AbstractToolProvider(toolsRegistry, events,
        CargoConstants.RUSTUP_CONFIG_NAME,
        CargoConstants.RUSTUP_CONFIG_PATH) {

    companion object {
        fun getHome(): File {
            System.getenv(CargoConstants.ENV_RUSTUP_HOME)?.let {
                return File(it)
            }
            return File(System.getProperty("user.home"), ".rustup")
        }
    }
}
