/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

/**
 * Cargo logger.
 */
interface CargoLogger {
    fun onEnter(text: String)
    fun processLine(text: String)
    fun processError(text: String)
    fun onLeave()
    fun canChangeState(state: CargoState, text: String): Boolean
}
