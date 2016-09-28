/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

/**
 * State-based logger.
 */
class CargoStateLogger(private val myLogger: BuildProgressLogger, private val myState: CargoState) : CargoDefaultLogger(myLogger) {

    override fun onEnter(text: String) {
        myLogger.message(String.format(MESSAGE_FORMAT, "$myState ${escapeValue(text)}"))
    }

    override fun processLine(text: String) {
        myLogger.message(String.format(MESSAGE_FORMAT, escapeValue(text)))
    }

    override fun onLeave() {
    }

    companion object {
        private val MESSAGE_FORMAT = "##teamcity[message text='%s']"
    }
}
