/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

import java.util.regex.Pattern

/**
 * Compiling logger.
 */
class CargoCompileLogger(private val myLogger: BuildProgressLogger) : CargoDefaultLogger(myLogger) {
    private var myProjectName: String? = null

    override fun onEnter(text: String) {
        val matcher = PROJECT_NAME_PATTERN.matcher(text)
        myProjectName = if (matcher.find()) ":" + matcher.group(1) else ""
        myLogger.message(String.format(COMPILATION_STARTED_FORMAT, myProjectName))

        val message = String.format("%s %s", CargoState.Compiling, text)
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, message))
    }

    override fun processLine(text: String) {
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, text.trim()))
    }

    override fun canChangeState(state: CargoState, text: String): Boolean {
        return !(state == CargoState.Running && text.startsWith("`rustc"))
    }

    override fun onLeave() {
        myLogger.message(String.format(COMPILATION_FINISHED_FORMAT, myProjectName))
    }

    companion object {
        private val PROJECT_NAME_PATTERN = Pattern.compile("([^\\s]+)")
        private const val COMPILATION_STARTED_FORMAT = "##teamcity[compilationStarted compiler='rustc%s']"
        private const val COMPILATION_FINISHED_FORMAT = "##teamcity[compilationFinished compiler='rustc%s']"
        private const val COMPILATION_MESSAGE_FORMAT = "##teamcity[message text='%s']"
    }
}
