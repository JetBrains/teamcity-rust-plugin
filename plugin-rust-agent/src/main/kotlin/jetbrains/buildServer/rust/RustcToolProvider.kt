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

/**
 * Determines rustc location.
 */
class RustcToolProvider(toolsRegistry: ToolProvidersRegistry,
                        events: EventDispatcher<AgentLifeCycleListener>)
    : AbstractToolProvider(toolsRegistry, events,
        CargoConstants.RUSTC_CONFIG_NAME,
        CargoConstants.RUSTC_CONFIG_PATH)
