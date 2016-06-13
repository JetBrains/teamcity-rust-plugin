/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust;

import jetbrains.buildServer.rust.commands.CommandType;
import jetbrains.buildServer.rust.commands.cargo.*;

import java.util.Arrays;
import java.util.List;

/**
 * Provides parameters for cargo runner.
 */
public class CargoParametersProvider {

    private final List<CommandType> myTypes;

    public CargoParametersProvider() {
        myTypes = Arrays.asList(
                new BuildCommandType(),
                new CleanCommandType(),
                new RunCommandType(),
                new TestCommandType());
    }

    public List<CommandType> getTypes() {
        return myTypes;
    }

    public String getCommandKey() {
        return CargoConstants.PARAM_COMMAND;
    }

    public String getVerbosityKey() {
        return CargoConstants.PARAM_VERBOSITY;
    }

    public String getBuildPackageKey() {
        return CargoConstants.PARAM_BUILD_PACKAGE;
    }

    public String getBuildTypeKey() {
        return CargoConstants.PARAM_BUILD_TYPE;
    }

    public String getBuildTypeNameKey() {
        return CargoConstants.PARAM_BUILD_TYPE_NAME;
    }

    public String getBuildFeaturesKey() {
        return CargoConstants.PARAM_BUILD_FEATURES;
    }

    public String getBuildNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_BUILD_NO_DEFAULT_FEATURES;
    }

    public String getBuildReleaseKey() {
        return CargoConstants.PARAM_BUILD_RELEASE;
    }

    public String getBuildTargetKey() {
        return CargoConstants.PARAM_BUILD_TARGET;
    }

    public String getBuildManifestKey() {
        return CargoConstants.PARAM_BUILD_MANIFEST;
    }

    public String getBuildParallelKey() {
        return CargoConstants.PARAM_BUILD_PARALLEL;
    }

    public String getCleanPackageKey() {
        return CargoConstants.PARAM_CLEAN_PACKAGE;
    }

    public String getCleanReleaseKey() {
        return CargoConstants.PARAM_CLEAN_RELEASE;
    }

    public String getCleanTargetKey() {
        return CargoConstants.PARAM_CLEAN_TARGET;
    }

    public String getCleanManifestKey() {
        return CargoConstants.PARAM_CLEAN_MANIFEST;
    }

    public String getTestArgumentsKey() {
        return CargoConstants.PARAM_TEST_ARGUMENTS;
    }

    public String getTestPackageKey() {
        return CargoConstants.PARAM_TEST_PACKAGE;
    }

    public String getTestTypeKey() {
        return CargoConstants.PARAM_TEST_TYPE;
    }

    public String getTestTypeNameKey() {
        return CargoConstants.PARAM_TEST_TYPE_NAME;
    }

    public String getTestReleaseKey() {
        return CargoConstants.PARAM_TEST_RELEASE;
    }

    public String getTestNoRunTestsKey() {
        return CargoConstants.PARAM_TEST_NO_RUN_TESTS;
    }

    public String getTestFeaturesKey() {
        return CargoConstants.PARAM_TEST_FEATURES;
    }

    public String getTestNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES;
    }

    public String getTestNoFailFastKey() {
        return CargoConstants.PARAM_TEST_NO_FAIL_FAST;
    }

    public String getTestTargetKey() {
        return CargoConstants.PARAM_TEST_TARGET;
    }

    public String getTestManifestKey() {
        return CargoConstants.PARAM_TEST_MANIFEST;
    }

    public String getTestParallelKey() {
        return CargoConstants.PARAM_TEST_PARALLEL;
    }

    public String getRunArgumentsKey() {
        return CargoConstants.PARAM_RUN_ARGUMENTS;
    }

    public String getRunTypeKey() {
        return CargoConstants.PARAM_RUN_TYPE;
    }

    public String getRunTypeNameKey() {
        return CargoConstants.PARAM_RUN_TYPE_NAME;
    }

    public String getRunReleaseKey() {
        return CargoConstants.PARAM_RUN_RELEASE;
    }

    public String getRunFeaturesKey() {
        return CargoConstants.PARAM_RUN_FEATURES;
    }

    public String getRunNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_RUN_NO_DEFAULT_FEATURES;
    }

    public String getRunTargetKey() {
        return CargoConstants.PARAM_RUN_TARGET;
    }

    public String getRunManifestKey() {
        return CargoConstants.PARAM_RUN_MANIFEST;
    }

    public String getRunParallelKey() {
        return CargoConstants.PARAM_RUN_PARALLEL;
    }
}