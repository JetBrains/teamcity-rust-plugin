/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

/**
 * Default logger implementation.
 */
open class CargoDefaultLogger(private val myLogger: BuildProgressLogger) : CargoLogger {

    override fun onEnter(text: String) {
        myLogger.message(text)
    }

    override fun processLine(text: String) {
        myLogger.message(text)
    }

    override fun processError(text: String) {
        myLogger.error(text)
    }

    override fun onLeave() {
    }

    override fun canChangeState(state: CargoState, text: String): Boolean {
        return true
    }
}
