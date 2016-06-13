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
                (CommandType)new BuildCommandType());
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
}