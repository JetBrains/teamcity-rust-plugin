

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

/**
 * State-based logger.
 */
class CargoStateLogger(private val myLogger: BuildProgressLogger,
                       private val myState: CargoState,
                       private val myPriority: String? = null)
    : CargoDefaultLogger(myLogger) {

    private var myMessage: StringBuilder? = null

    override fun onEnter(text: String) {
        if (myPriority.isNullOrEmpty()) {
            myLogger.message(String.format(MESSAGE_FORMAT, "$myState ${escapeValue(text)}", ""))
        } else {
            myMessage = StringBuilder("$myState $text")
        }
    }

    override fun processLine(text: String) {
        if (myMessage == null) {
            myLogger.message(String.format(MESSAGE_FORMAT, escapeValue(text), ""))
        } else {
            myMessage?.append(text)?.append("\n")
        }
    }

    override fun onLeave() {
        if (myMessage != null) {
            val message = escapeValue(myMessage.toString().trim())
            myLogger.message(String.format(MESSAGE_FORMAT, message, " status='$myPriority'"))
            myMessage = null
        }
    }

    companion object {
        private const val MESSAGE_FORMAT = "##teamcity[message text='%s'%s]"
    }
}