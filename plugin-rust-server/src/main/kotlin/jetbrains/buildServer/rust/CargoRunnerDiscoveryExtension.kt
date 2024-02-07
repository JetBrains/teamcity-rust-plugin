

package jetbrains.buildServer.rust

import jetbrains.buildServer.serverSide.discovery.BreadthFirstRunnerDiscoveryExtension
import jetbrains.buildServer.serverSide.discovery.DiscoveredObject
import jetbrains.buildServer.util.browser.Element
import java.util.*

/**
 * Performs cargo build steps discovery.
 */
class CargoRunnerDiscoveryExtension : BreadthFirstRunnerDiscoveryExtension(1) {
    override fun discoverRunnersInDirectory(dir: Element, filesAndDirs: MutableList<Element>): MutableList<DiscoveredObject> {
        val result = ArrayList<DiscoveredObject>()
        for (item in filesAndDirs) {
            if (item.isLeaf && item.name == CargoConstants.PROJECT_FILE) {
                result.add(DiscoveredObject(CargoConstants.RUNNER_TYPE,
                        mapOf(Pair(CargoConstants.PARAM_COMMAND, CargoConstants.COMMAND_BUILD))))
                result.add(DiscoveredObject(CargoConstants.RUNNER_TYPE,
                        mapOf(Pair(CargoConstants.PARAM_COMMAND, CargoConstants.COMMAND_TEST))))
            }
        }

        return result
    }
}