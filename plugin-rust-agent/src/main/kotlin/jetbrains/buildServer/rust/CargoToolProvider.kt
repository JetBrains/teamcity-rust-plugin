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
import java.util.regex.Pattern

/**
 * Determines cargo location.
 */
class CargoToolProvider(toolProvidersRegistry: ToolProvidersRegistry) : ToolProvider {

    init {
        toolProvidersRegistry.registerToolProvider(this)
    }

    override fun supports(toolName: String): Boolean {
        return CargoConstants.RUNNER_TYPE.equals(toolName, ignoreCase = true)
    }

    @Throws(ToolCannotBeFoundException::class)
    override fun getPath(toolName: String): String {
        val pathVariable = System.getenv("PATH")
        val paths = StringUtil.splitHonorQuotes(pathVariable, File.pathSeparatorChar)

        return FileUtils.findToolPath(paths, TOOL_PATTERN) ?:
                throw ToolCannotBeFoundException("""
                Unable to locate tool $toolName in system. Please make sure to add it in the PATH variable
                """.trimIndent())
    }

    @Throws(ToolCannotBeFoundException::class)
    override fun getPath(toolName: String,
                         build: AgentRunningBuild,
                         runner: BuildRunnerContext): String {
        return getPath(toolName)
    }

    companion object {
        private val TOOL_PATTERN = Pattern.compile("^.*" + CargoConstants.RUNNER_TYPE + "(\\.(exe))?$")
    }
}
