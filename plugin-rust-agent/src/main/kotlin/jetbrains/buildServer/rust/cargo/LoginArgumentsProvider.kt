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
 * Provides arguments to cargo login command.
 */
class LoginArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_LOGIN)

        val hostValue = parameters[CargoConstants.PARAM_LOGIN_HOST]
        if (!hostValue.isNullOrBlank()) {
            arguments.add("--host")
            arguments.add(hostValue.trim())
        }

        val verbosityValue = parameters[CargoConstants.PARAM_VERBOSITY]
        if (!verbosityValue.isNullOrBlank()) {
            arguments.add(verbosityValue.trim())
        }

        val tokenValue = parameters[CargoConstants.PARAM_LOGIN_TOKEN]
        if (!tokenValue.isNullOrBlank()) {
            arguments.add(tokenValue.trim())
        }

        return arguments
    }
}
