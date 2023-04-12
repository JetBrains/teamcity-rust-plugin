/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import java.lang.IllegalStateException
import java.util.*

/**
 * Provides arguments to cargo clean command.
 */
class CustomCrateArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val command = runnerContext.runnerParameters[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
            ?: throw IllegalStateException("Custom crate name should be provided")
        return listOf(command)
    }

    override fun shouldFailBuildIfCommandFailed(): Boolean = true
}
