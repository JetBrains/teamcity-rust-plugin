package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger
import java.util.regex.Pattern

/**
 * Testing logger.
 */
class CargoTestingLogger(private val myLogger: BuildProgressLogger) : CargoDefaultLogger(myLogger) {
    private var myTestSuiteName: String? = null
    private var myTestName: String? = null
    private var myTestStartTime: Long = 0
    private var myTestOutputName: String? = null
    private var myFailedTests = mutableMapOf<String, Pair<Long, StringBuilder>>()

    override fun onEnter(text: String) {
        myTestSuiteName = if (text.isBlank()) null else text
        myFailedTests = hashMapOf()
        myTestStartTime = System.currentTimeMillis()
        myTestOutputName = null

        if (myTestSuiteName != null) {
            myLogger.message(String.format(TEST_SUITE_STARTED_FORMAT, myTestSuiteName))
        }
    }

    override fun canChangeState(state: CargoState, text: String): Boolean {
        return myTestOutputName == null
    }

    override fun processLine(text: String) {
        val line = text.trim()
        if (line == "failures:") {
            myTestOutputName = null
            return
        }

        val testMatcher = TEST_PATTERN.matcher(line)
        if (testMatcher.find()) {
            // Test result line
            val testName = testMatcher.group(1).replace('\\', '/')
            val result = testMatcher.group(2).lowercase()
            myTestName = testName

            val testDuration = System.currentTimeMillis() - myTestStartTime
            when (result) {
                "ok" -> {
                    myLogger.message(String.format(TEST_STARTED_FORMAT, testName))
                    myLogger.message(String.format(TEST_FINISHED_FORMAT, testName, testDuration))
                }
                "ignored" -> {
                    myLogger.message(String.format(TEST_IGNORED_FORMAT, testName, ""))
                }
                "failed" -> {
                    myFailedTests.set(testName, Pair(testDuration, StringBuilder()))
                }
                else -> {
                    myLogger.message(String.format(TEST_STDOUT_FORMAT, myTestName, escapeValue(line)))
                }
            }

            myTestStartTime = System.currentTimeMillis()
        } else {
            val outputMatcher = TEST_STDOUT_PATTERN.matcher(line)
            if (outputMatcher.find()) {
                // Test output line
                val testName = outputMatcher.group(1)
                if (myFailedTests.containsKey(testName) == true) {
                    myTestOutputName = testName
                    return
                }
            }

            if (!myTestOutputName.isNullOrEmpty()) {
                val pair = myFailedTests.get(myTestOutputName!!)
                pair?.second?.append(line)?.append("\n")
            }
        }
    }

    override fun onLeave() {
        myFailedTests.forEach {
            myLogger.message(String.format(TEST_STARTED_FORMAT, it.key))
            val text = it.value.second.trimEnd('\n').toString()
            val index = text.indexOfAny(arrayListOf(": ", ", "))
            val error = if (index > 0) text.substring(0, index) else text
            myLogger.message(String.format(TEST_FAILED_FORMAT, it.key, escapeValue(error), escapeValue(text)))
            myLogger.message(String.format(TEST_FINISHED_FORMAT, it.key, it.value.first))
        }

        if (myTestSuiteName != null) myLogger.message(String.format(TEST_SUITE_FINISHED_FORMAT, myTestSuiteName))
    }

    companion object {
        private val TEST_PATTERN = Pattern.compile(
                "^\\s*test\\s+(.+)\\s\\.\\.\\.\\s(ok|failed|ignored|bench)", Pattern.CASE_INSENSITIVE)
        private val TEST_STDOUT_PATTERN = Pattern.compile("^---- ([^\\s]+) stdout ----")
        private const val TEST_SUITE_STARTED_FORMAT = "##teamcity[testSuiteStarted name='%s']"
        private const val TEST_SUITE_FINISHED_FORMAT = "##teamcity[testSuiteFinished name='%s']"
        private const val TEST_STARTED_FORMAT = "##teamcity[testStarted name='%s']"
        private const val TEST_FINISHED_FORMAT = "##teamcity[testFinished name='%s' duration='%s']"
        private const val TEST_IGNORED_FORMAT = "##teamcity[testIgnored name='%s' message='%s']"
        private const val TEST_FAILED_FORMAT = "##teamcity[testFailed name='%s' message='%s' details='%s']"
        private const val TEST_STDOUT_FORMAT = "##teamcity[testStdOut name='%s' out='%s']"
    }
}
