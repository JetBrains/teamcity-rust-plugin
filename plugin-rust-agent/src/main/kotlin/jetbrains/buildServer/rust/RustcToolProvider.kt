

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
        CargoConstants.RUSTC_CONFIG_PATH,
        CargoConstants.RUSTC_EXECUTABLE_NAME
)