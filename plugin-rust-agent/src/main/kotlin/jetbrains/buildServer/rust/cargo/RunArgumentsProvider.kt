/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo

import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.util.StringUtil
import java.util.ArrayList

/**
 * Provides arguments to cargo run command.
 */
class RunArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_RUN)

        val parallelJobsValue = parameters[CargoConstants.PARAM_RUN_PARALLEL]
        if (!parallelJobsValue.isNullOrBlank()) {
            arguments.add("--jobs")
            arguments.add(parallelJobsValue!!.trim())
        }

        val typeValue = parameters[CargoConstants.PARAM_RUN_TYPE]
        if (!typeValue.isNullOrBlank()) {
            arguments.add(typeValue!!.trim())
            val typeNameValue = parameters[CargoConstants.PARAM_RUN_TYPE_NAME]
            if (!typeNameValue.isNullOrBlank()) {
                arguments.add(typeNameValue!!.trim())
            }
        }

        val releaseValue = parameters[CargoConstants.PARAM_RUN_RELEASE]
        if ("true".equals(releaseValue, ignoreCase = true)) {
            arguments.add("--release")
        }

        val featuresValue = parameters[CargoConstants.PARAM_RUN_FEATURES]
        if (!featuresValue.isNullOrBlank()) {
            arguments.add("--features")
            arguments.add(featuresValue!!.trim())
        }

        val noDefaultFeaturesValue = parameters[CargoConstants.PARAM_RUN_NO_DEFAULT_FEATURES]
        if ("true".equals(noDefaultFeaturesValue, ignoreCase = true)) {
            arguments.add("--no-default-features")
        }

        val targetValue = parameters[CargoConstants.PARAM_RUN_TARGET]
        if (!targetValue.isNullOrBlank()) {
            arguments.add("--target")
            arguments.add(targetValue!!.trim())
        }

        val manifestValue = parameters[CargoConstants.PARAM_RUN_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue!!.trim())
        }

        val verbosityValue = parameters[CargoConstants.PARAM_VERBOSITY]
        if (!verbosityValue.isNullOrBlank()) {
            arguments.add(verbosityValue!!.trim())
        }

        val argumentsValue = parameters[CargoConstants.PARAM_RUN_ARGUMENTS]
        if (!argumentsValue.isNullOrBlank()) {
            arguments.addAll(StringUtil.splitCommandArgumentsAndUnquote(argumentsValue!!))
        }

        return arguments
    }
}
