/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import java.util.regex.Pattern

/**
 * Handles build messages from cargo tool.
 */
class CargoLoggingListener(private val myLoggerFactory: CargoLoggerFactory) : ProcessListenerAdapter() {
    private var myLogger: CargoLogger
    private var myLastLine: String? = null

    init {
        myLogger = myLoggerFactory.getLogger(CargoState.Default)
    }

    override fun onStandardOutput(text: String) {
        val line = text.trim()
        if (line.isNullOrBlank()) return

        val lastLine = myLastLine
        myLastLine = line

        val testsMatcher = myTestsStart.matcher(line)
        if (testsMatcher.find()) {
            val testSuiteName = getTestSuiteName(lastLine!!)
            changeState(CargoState.Testing, testSuiteName)
            return
        }

        val stateMatcher = myStatement.matcher(line)
        if (stateMatcher.find()) {
            val stateKey = stateMatcher.group(1)
            val stateText = stateMatcher.group(2)

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

    companion object {
        private val myStatement = Pattern.compile("^([\\w][\\w-]+\\:?)\\s+(.*)?$")
        private val myTestsStart = Pattern.compile("^running \\d+ tests?$")

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
