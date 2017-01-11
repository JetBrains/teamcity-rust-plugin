/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.*
import jetbrains.buildServer.util.StringUtil

import java.io.File

/**
 * Determines cargo location.
 */
class CargoToolProvider(toolProvidersRegistry: ToolProvidersRegistry) : ToolProvider {

    private val patterns = hashMapOf(
            "cargo" to Regex("^.*cargo(\\.(exe))?$", RegexOption.IGNORE_CASE),
            "rustc" to Regex("^.*rustc(\\.(exe))?$", RegexOption.IGNORE_CASE))

    init {
        toolProvidersRegistry.registerToolProvider(this)
    }

    override fun supports(toolName: String): Boolean {
        return patterns.containsKey(toolName.toLowerCase())
    }

    override fun getPath(toolName: String): String {
        val pattern = patterns[toolName.toLowerCase()] ?:
                throw ToolCannotBeFoundException("Unsupported tool $toolName")

        val pathVariable = System.getenv("PATH")
        val paths = StringUtil.splitHonorQuotes(pathVariable, File.pathSeparatorChar)

        return FileUtils.findToolPath(paths, pattern) ?:
                throw ToolCannotBeFoundException("""
                Unable to locate tool $toolName in system. Please make sure to add it in the PATH variable
                """.trimIndent())
    }

    override fun getPath(toolName: String,
                         build: AgentRunningBuild,
                         runner: BuildRunnerContext): String {
        return getPath(toolName)
    }
}
