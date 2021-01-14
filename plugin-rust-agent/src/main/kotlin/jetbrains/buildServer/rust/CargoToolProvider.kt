/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.AgentLifeCycleListener
import jetbrains.buildServer.agent.ToolProvidersRegistry
import jetbrains.buildServer.util.EventDispatcher

/**
 * Determines cargo tool location.
 */
class CargoToolProvider(toolsRegistry: ToolProvidersRegistry,
                        events: EventDispatcher<AgentLifeCycleListener>)
    : AbstractToolProvider(toolsRegistry, events,
        CargoConstants.CARGO_CONFIG_NAME,
        CargoConstants.CARGO_CONFIG_PATH,
        CargoConstants.CARGO_EXECUTABLE_NAME
)
