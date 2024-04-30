package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import java.util.concurrent.atomic.AtomicBoolean

class ErrorDetectingListener(private val logger: BuildProgressLogger, private val errorFlag: AtomicBoolean) : ProcessListenerAdapter() {
    override fun onStandardOutput(text: String) = processOutput(text)
    override fun onErrorOutput(text: String) = processOutput(text)

    private fun processOutput(text: String) {
        if (text.startsWith("error:")) {
            errorFlag.set(true)
            logger.error(text)
        } else {
            logger.message(text)
        }
    }
}
