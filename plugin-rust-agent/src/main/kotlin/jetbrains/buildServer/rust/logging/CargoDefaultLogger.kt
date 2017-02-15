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

    companion object {
        private val tokens = mapOf(
                Pair("'", "|'"),
                Pair("\n", "|n"),
                Pair("\r", "|r"),
                Pair("|", "||"),
                Pair("[", "|["),
                Pair("]", "|]"))

        private val ESCAPE_PATTERN = Pattern.compile(tokens.keys.joinToString(
                separator = "|",
                prefix = "(",
                postfix = ")",
                transform = { Pattern.quote(it) }))

        fun escapeValue(text: String): String {
            val matcher = ESCAPE_PATTERN.matcher(text)

            val sb = StringBuffer()
            while (matcher.find()) {
                matcher.appendReplacement(sb, tokens[matcher.group(1)])
            }

            return matcher.appendTail(sb).toString()
        }
    }
}
