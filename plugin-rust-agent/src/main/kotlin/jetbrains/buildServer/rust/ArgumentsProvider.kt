/*
 * Copyright 2000-2023 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.util.StringUtil

/**
 * Provides arguments to the utility.
 */
interface ArgumentsProvider {
    fun getArguments(runnerContext: BuildRunnerContext): List<String>
    fun shouldFailBuildIfCommandFailed(): Boolean = true

    fun addCommonArguments(parameters: Map<String, String>, arguments: MutableList<String>) {
        val verbosityValue = parameters[CargoConstants.PARAM_VERBOSITY]
        if (!verbosityValue.isNullOrBlank()) {
            arguments += verbosityValue.trim()
        }

        val configValue = parameters[CargoConstants.PARAM_CONFIG]
        if (!configValue.isNullOrBlank()) {
            StringUtil.splitHonorQuotes(configValue).forEach { value ->
                arguments += "--config"
                arguments += value
            }
        }
    }
}
