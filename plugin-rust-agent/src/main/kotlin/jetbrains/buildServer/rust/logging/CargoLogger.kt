

package jetbrains.buildServer.rust.logging

/**
 * Cargo logger.
 */
interface CargoLogger {
    fun onEnter(text: String)
    fun processLine(text: String)
    fun processError(text: String)
    fun onLeave()
    fun canChangeState(state: CargoState, text: String): Boolean
}