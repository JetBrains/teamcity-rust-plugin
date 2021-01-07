/*
 * Copyright 2000-2020 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.util.*

/**
 * Provides arguments to cargo publish command.
 */
class PublishArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_PUBLISH)

        val hostValue = parameters[CargoConstants.PARAM_PUBLISH_HOST]
        if (!hostValue.isNullOrBlank()) {
            arguments.add("--host")
            arguments.add(hostValue.trim())
        }

        val tokenValue = parameters[CargoConstants.PARAM_PUBLISH_TOKEN_SECURE] ?: parameters[CargoConstants.PARAM_PUBLISH_TOKEN]
        if (!tokenValue.isNullOrBlank()) {
            arguments.add("--token")
            arguments.add(tokenValue.trim())
        }

        val noVerifyValue = parameters[CargoConstants.PARAM_PUBLISH_NO_VERIFY]
        if ("true".equals(noVerifyValue, ignoreCase = true)) {
            arguments.add("--no-verify")
        }

        val manifestValue = parameters[CargoConstants.PARAM_PUBLISH_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue.trim())
        }

        val verbosityValue = parameters[CargoConstants.PARAM_VERBOSITY]
        if (!verbosityValue.isNullOrBlank()) {
            arguments.add(verbosityValue.trim())
        }

        return arguments
    }
}
