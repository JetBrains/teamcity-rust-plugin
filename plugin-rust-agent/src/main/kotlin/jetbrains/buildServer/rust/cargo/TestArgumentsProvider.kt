/*
 * Copyright 2000-2017 JetBrains s.r.o.
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
 * Provides arguments to cargo test command.
 */
class TestArgumentsProvider : ArgumentsProvider {

    override fun getArguments(runnerContext: BuildRunnerContext): List<String> {
        val parameters = runnerContext.runnerParameters
        val arguments = ArrayList<String>()
        arguments.add(CargoConstants.COMMAND_TEST)

        val packageValue = parameters[CargoConstants.PARAM_TEST_PACKAGE]
        if (!packageValue.isNullOrBlank()) {
            arguments.add("--package")
            arguments.add(packageValue!!.trim())
        }

        val parallelJobsValue = parameters[CargoConstants.PARAM_TEST_PARALLEL]
        if (!parallelJobsValue.isNullOrBlank()) {
            arguments.add("--jobs")
            arguments.add(parallelJobsValue!!.trim())
        }

        val typeValue = parameters[CargoConstants.PARAM_TEST_TYPE]
        if (!typeValue.isNullOrBlank()) {
            arguments.add(typeValue!!.trim())
            val typeNameValue = parameters[CargoConstants.PARAM_TEST_TYPE_NAME]
            if (!typeNameValue.isNullOrBlank()) {
                arguments.add(typeNameValue!!.trim())
            }
        }

        val releaseValue = parameters[CargoConstants.PARAM_TEST_RELEASE]
        if ("true".equals(releaseValue, ignoreCase = true)) {
            arguments.add("--release")
        }

        val noRunTestsValue = parameters[CargoConstants.PARAM_TEST_NO_RUN]
        if ("true".equals(noRunTestsValue, ignoreCase = true)) {
            arguments.add("--no-run")
        }

        val failFastValue = parameters[CargoConstants.PARAM_TEST_NO_FAIL_FAST]
        if ("true".equals(failFastValue, ignoreCase = true)) {
            arguments.add("--no-fail-fast")
        }

        val featuresValue = parameters[CargoConstants.PARAM_TEST_FEATURES]
        if (!featuresValue.isNullOrBlank()) {
            arguments.add("--features")
            arguments.add(featuresValue!!.trim())
        }

        val noDefaultFeaturesValue = parameters[CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES]
        if ("true".equals(noDefaultFeaturesValue, ignoreCase = true)) {
            arguments.add("--no-default-features")
        }

        val targetValue = parameters[CargoConstants.PARAM_TEST_TARGET]
        if (!targetValue.isNullOrBlank()) {
            arguments.add("--target")
            arguments.add(targetValue!!.trim())
        }

        val manifestValue = parameters[CargoConstants.PARAM_TEST_MANIFEST]
        if (!manifestValue.isNullOrBlank()) {
            arguments.add("--manifest-path")
            arguments.add(manifestValue!!.trim())
        }

        val verbosityValue = parameters[CargoConstants.PARAM_VERBOSITY]
        if (!verbosityValue.isNullOrBlank()) {
            arguments.add(verbosityValue!!.trim())
        }

        val argumentsValue = parameters[CargoConstants.PARAM_TEST_ARGUMENTS]
        if (!argumentsValue.isNullOrBlank()) {
            arguments.addAll(StringUtil.splitCommandArgumentsAndUnquote(argumentsValue!!))
        }

        return arguments
    }
}
