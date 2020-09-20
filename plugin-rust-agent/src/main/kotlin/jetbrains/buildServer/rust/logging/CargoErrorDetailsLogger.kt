/*
 * Copyright 2000-2020 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

/**
 * Error details logger.
 */
class CargoErrorDetailsLogger(private val myLogger: BuildProgressLogger) : CargoDefaultLogger(myLogger) {
    private var myErrorDetails: StringBuilder? = null

    override fun onEnter(text: String) {
        myErrorDetails = StringBuilder(text)
    }

    override fun canChangeState(state: CargoState, text: String) = true

    override fun processLine(text: String) {
        myErrorDetails?.append("\n")?.append(text)
    }

    override fun onLeave() {
        myErrorDetails.let {
            myLogger.message(String.format(BUILD_PROBLEM_FORMAT, escapeValue(myErrorDetails.toString())))
        }
    }

    companion object {
        private const val BUILD_PROBLEM_FORMAT = "##teamcity[buildProblem description='%s']"
    }
}
