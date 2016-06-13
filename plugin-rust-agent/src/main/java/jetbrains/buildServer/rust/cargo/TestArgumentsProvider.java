/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.cargo;

import jetbrains.buildServer.rust.ArgumentsProvider;
import jetbrains.buildServer.rust.CargoConstants;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Provides arguments to cargo test command.
 */
public class TestArgumentsProvider implements ArgumentsProvider {

    @NotNull
    @Override
    public List<String> getArguments(@NotNull final Map<String, String> parameters) {
        final List<String> arguments = new ArrayList<String>();
        arguments.add(CargoConstants.COMMAND_TEST);

        final String packageValue = parameters.get(CargoConstants.PARAM_TEST_PACKAGE);
        if (!StringUtil.isEmptyOrSpaces(packageValue)) {
            arguments.add("--package");
            arguments.add(packageValue.trim());
        }

        final String parallelJobsValue = parameters.get(CargoConstants.PARAM_TEST_PARALLEL);
        if (!StringUtil.isEmptyOrSpaces(parallelJobsValue)) {
            arguments.add("--jobs");
            arguments.add(parallelJobsValue.trim());
        }

        final String typeValue = parameters.get(CargoConstants.PARAM_TEST_TYPE);
        if (!StringUtil.isEmptyOrSpaces(typeValue)) {
            arguments.add(typeValue.trim());
            final String typeNameValue = parameters.get(CargoConstants.PARAM_TEST_TYPE_NAME);
            if (!StringUtil.isEmptyOrSpaces(typeNameValue)) {
                arguments.add(typeNameValue.trim());
            }
        }

        final String releaseValue = parameters.get(CargoConstants.PARAM_TEST_RELEASE);
        if ("true".equalsIgnoreCase(releaseValue)) {
            arguments.add("--release");
        }

        final String noRunTestsValue = parameters.get(CargoConstants.PARAM_TEST_NO_RUN_TESTS);
        if ("true".equalsIgnoreCase(noRunTestsValue)) {
            arguments.add("--no-run");
        }

        final String failFastValue = parameters.get(CargoConstants.PARAM_TEST_NO_FAIL_FAST);
        if ("true".equalsIgnoreCase(failFastValue)) {
            arguments.add("--no-fail-fast");
        }

        final String featuresValue = parameters.get(CargoConstants.PARAM_TEST_FEATURES);
        if (!StringUtil.isEmptyOrSpaces(featuresValue)) {
            arguments.add("--features");
            arguments.add(featuresValue.trim());
        }

        final String noDefaultFeaturesValue = parameters.get(CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES);
        if ("true".equalsIgnoreCase(noDefaultFeaturesValue)) {
            arguments.add("--no-default-features");
        }

        final String targetValue = parameters.get(CargoConstants.PARAM_TEST_TARGET);
        if (!StringUtil.isEmptyOrSpaces(targetValue)) {
            arguments.add("--target");
            arguments.add(targetValue.trim());
        }

        final String manifestValue = parameters.get(CargoConstants.PARAM_TEST_MANIFEST);
        if (!StringUtil.isEmptyOrSpaces(manifestValue)) {
            arguments.add("--manifest-path");
            arguments.add(manifestValue.trim());
        }

        final String verbosityValue = parameters.get(CargoConstants.PARAM_VERBOSITY);
        if (!StringUtil.isEmptyOrSpaces(verbosityValue)) {
            arguments.add(verbosityValue.trim());
        }

        final String argumentsValue = parameters.get(CargoConstants.PARAM_TEST_ARGUMENTS);
        if (!StringUtil.isEmptyOrSpaces(argumentsValue)) {
            arguments.addAll(StringUtil.splitCommandArgumentsAndUnquote(argumentsValue));
        }

        return arguments;
    }
}
