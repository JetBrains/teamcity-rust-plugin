/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import java.io.File

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
            val testSuiteName = getTestSuiteName(lastLine!!.trim())
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

    override fun processStarted(programCommandLine: String, workingDirectory: File) {
        val commandLine: String
        val result = myCargoCommandLine.find(programCommandLine)
        commandLine = if (result == null) {
            programCommandLine
        } else {
            val (_, arguments) = result.destructured
            "cargo ${arguments.removeSuffix(" 2>&1")}"
        }

        myLoggerFactory.logger.message("Starting: $commandLine")
        myLoggerFactory.logger.message("in directory: $workingDirectory")
    }

    override fun onErrorOutput(text: String) {
        myLogger.processError(text)
    }

    override fun processFinished(exitCode: Int) {
        myLogger.onLeave()
    }

    companion object {
        private val myStatement = Regex("^\\s*([\\w][\\w-]+:?)\\s+(.*)?$")
        private val myTestsStart = Regex("^\\s*running \\d+ tests?$")
        private val myErrorStart = Regex("^\\s*error\\[E\\d+]:\\s.+$")
        private val myCargoCommandLine = Regex("cargo(\\.exe)?\\s(.*)")

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
