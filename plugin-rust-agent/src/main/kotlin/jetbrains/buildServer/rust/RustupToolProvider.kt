

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
        CargoConstants.RUSTUP_CONFIG_PATH,
        CargoConstants.RUSTUP_EXECUTABLE_NAME
) {

    companion object {
        fun getHome(): File {
            System.getenv(CargoConstants.ENV_RUSTUP_HOME)?.let {
                return File(it)
            }
            return File(System.getProperty("user.home"), ".rustup")
        }
    }
}