package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.runner.ProcessListenerAdapter

/**
 * Handles build messages from cargo tool.
 */
class CargoLoggingListener(private val myLoggerFactory: CargoLoggerFactory) : ProcessListenerAdapter() {
    private var myLogger: CargoLogger
    private var myLastLine: String? = null

    init {
        myLogger = myLoggerFactory.getLogger(CargoState.Default)
    }

    override fun onStandardOutput(line: String) {
        if (line.isBlank()) return

        val lastLine = myLastLine
        myLastLine = line

        myTestsStart.find(line)?.let {
            val testSuiteText = lastLine ?: ""
            val testSuiteName = getTestSuiteName(testSuiteText.trim())
            changeState(CargoState.Testing, testSuiteName)
            return
        }

        myErrorStart.find(line)?.let {
            changeState(CargoState.ErrorDetails, line)
            return
        }

        myStatement.find(line)?.let {
            val (stateKey, stateText) = it.destructured

            val state = CargoState[stateKey]
            if (state != null && myLogger.canChangeState(state, stateText)) {
                changeState(state, stateText)
                return
            }
        }

        myLogger.processLine(line)
    }

    private fun changeState(state: CargoState, text: String) {
        val logger = myLoggerFactory.getLogger(state)

        myLogger.onLeave()
        logger.onEnter(text)
        myLogger = logger
    }

    override fun onErrorOutput(text: String) {
        myLogger.processError(text)
    }

    override fun processFinished(exitCode: Int) {
        myLogger.onLeave()
    }

    private companion object {
        private val myStatement = Regex("^\\s*([\\w][\\w-]+:?)\\s+(.*)?$")
        private val myTestsStart = Regex("^\\s*running \\d+ tests?$")
        private val myErrorStart = Regex("^\\s*error\\[E\\d+]:\\s.+$")

        private fun getTestSuiteName(text: String): String {
            if (text.startsWith(CargoState.Running.toString())) {
                var slashIndex = text.lastIndexOf("/")
                if (slashIndex < 0) {
                    slashIndex = text.lastIndexOf("\\")
                }

                if (slashIndex > 0) {
                    val dashIndex = text.lastIndexOf("-")
                    if (dashIndex > slashIndex) {
                        return text.substring(slashIndex + 1, dashIndex)
                    }
                }
            } else if (text.startsWith(CargoState.DocTests.toString())) {
                return text
            }

            return ""
        }
    }
}
