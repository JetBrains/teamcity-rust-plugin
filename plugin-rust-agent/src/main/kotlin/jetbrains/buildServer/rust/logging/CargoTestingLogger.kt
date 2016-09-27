/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger
import java.util.regex.Pattern

/**
 * Testing logger.
 */
class CargoTestingLogger(private val myLogger: BuildProgressLogger) : CargoDefaultLogger(myLogger) {
    private var myTestSuiteName: String? = null
    private var myTestName: String? = null
    private var testStartTime: Long = 0

    override fun onEnter(text: String) {
        myTestSuiteName = text
        testStartTime = System.currentTimeMillis()

        myLogger.message(String.format(TEST_SUITE_STARTED_FORMAT, myTestSuiteName))
    }

    override fun processLine(text: String) {
        if (text.startsWith("test result")) return

        val matcher = TEST_PATTERN.matcher(text)
        if (matcher.find()) {
            val testName = matcher.group(1)
            val result = matcher.group(2).toLowerCase()

            myLogger.message(String.format(TEST_STARTED_FORMAT, testName))
            myTestName = testName

            if ("ok" == result) {
                val testDuration = System.currentTimeMillis() - testStartTime
                myLogger.message(String.format(TEST_FINISHED_FORMAT, testName, testDuration))
            } else if ("ignored" == result) {
                myLogger.message(String.format(TEST_IGNORED_FORMAT, testName, ""))
            } else if ("failed" == result) {
                myLogger.message(String.format(TEST_FAILED_FORMAT, testName, "", ""))
            } else {
                myLogger.message(String.format(TEST_STDOUT_FORMAT, myTestName, text))
            }
        } else {
            myLogger.message(String.format(TEST_STDOUT_FORMAT, myTestName, text))
        }

        testStartTime = System.currentTimeMillis()
    }

    override fun onLeave() {
        if (myTestSuiteName != null) myLogger.message(String.format(TEST_SUITE_FINISHED_FORMAT, myTestSuiteName))
    }

    companion object {
        private val TEST_PATTERN = Pattern.compile(
                "^test\\s([^\\s]+)\\s\\.\\.\\.\\s(ok|failed|ignored|bench)", Pattern.CASE_INSENSITIVE)
        private val TEST_SUITE_STARTED_FORMAT = "##teamcity[testSuiteStarted name='%s']"
        private val TEST_SUITE_FINISHED_FORMAT = "##teamcity[testSuiteFinished name='%s']"
        private val TEST_STARTED_FORMAT = "##teamcity[testStarted name='%s']"
        private val TEST_FINISHED_FORMAT = "##teamcity[testFinished name='%s' duration='%s']"
        private val TEST_IGNORED_FORMAT = "##teamcity[testIgnored name='%s' message='%s']"
        private val TEST_FAILED_FORMAT = "##teamcity[testFailed name='%s' message='%s' details='%s']"
        private val TEST_STDOUT_FORMAT = "##teamcity[testStdOut name='%s' out='%s']"
    }
}
