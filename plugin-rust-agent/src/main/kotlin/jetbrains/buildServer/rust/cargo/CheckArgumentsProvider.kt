/*
 * Copyright 2000-2023 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants

/**
 * Provides arguments to `cargo check` command.
 */
class CheckArgumentsProvider : ArgumentsProvider {
    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments += CargoConstants.COMMAND_CHECK

        val packageValue = parameters[CargoConstants.PARAM_CHECK_PACKAGE]
        if (!packageValue.isNullOrBlank()) {
            arguments += "--package"
            arguments += packageValue.trim()
        }

        val typeValue = parameters[CargoConstants.PARAM_CHECK_TYPE]
        if (!typeValue.isNullOrBlank()) {
            arguments += typeValue.trim()
            val typeNameValue = parameters[CargoConstants.PARAM_CHECK_TYPE_NAME]
            if (!typeNameValue.isNullOrBlank()) {
                arguments += typeNameValue.trim()
            }
        }

        val checkFeaturesValue = parameters[CargoConstants.PARAM_CHECK_FEATURES]
        if (!checkFeaturesValue.isNullOrBlank()) {
            arguments += "--features"
            arguments += checkFeaturesValue.trim()
        }

        val noDefaultFeaturesValue = parameters[CargoConstants.PARAM_CHECK_NO_DEFAULT_FEATURES]
        if ("true".equals(noDefaultFeaturesValue, ignoreCase = true)) {
            arguments += "--no-default-features"
        }

        val targetValue = parameters[CargoConstants.PARAM_CHECK_TARGET]
        if (!targetValue.isNullOrBlank()) {
            arguments += "--target"
            arguments += targetValue.trim()
        }

        val releaseValue = parameters[CargoConstants.PARAM_CHECK_RELEASE]
        if ("true".equals(releaseValue, ignoreCase = true)) {
            arguments += "--release"
        }

        val manifestValue = parameters[CargoConstants.PARAM_CHECK_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments += "--manifest-path"
            arguments += manifestValue.trim()
        }

        val parallelJobsValue = parameters[CargoConstants.PARAM_CHECK_PARALLEL]
        if (!parallelJobsValue.isNullOrBlank()) {
            arguments += "--jobs"
            arguments += parallelJobsValue.trim()
        }

        addCommonArguments(parameters, arguments)

        return arguments
    }
}
