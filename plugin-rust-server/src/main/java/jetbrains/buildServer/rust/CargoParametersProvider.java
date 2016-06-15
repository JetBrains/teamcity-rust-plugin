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
                new BenchCommandType(),
                new BuildCommandType(),
                new CleanCommandType(),
                new DocCommandType(),
                new LoginCommandType(),
                new PackageCommandType(),
                new PublishCommandType(),
                new RunCommandType(),
                new RustcCommandType(),
                new RustDocCommandType(),
                new TestCommandType(),
                new UpdateCommandType(),
                new YankCommandType());
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

    public String getTestNoRunKey() {
        return CargoConstants.PARAM_TEST_NO_RUN;
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

    public String getBenchArgumentsKey() {
        return CargoConstants.PARAM_BENCH_ARGUMENTS;
    }

    public String getBenchPackageKey() {
        return CargoConstants.PARAM_BENCH_PACKAGE;
    }

    public String getBenchTypeKey() {
        return CargoConstants.PARAM_BENCH_TYPE;
    }

    public String getBenchTypeNameKey() {
        return CargoConstants.PARAM_BENCH_TYPE_NAME;
    }

    public String getBenchReleaseKey() {
        return CargoConstants.PARAM_BENCH_RELEASE;
    }

    public String getBenchNoRunKey() {
        return CargoConstants.PARAM_BENCH_NO_RUN;
    }

    public String getBenchFeaturesKey() {
        return CargoConstants.PARAM_BENCH_FEATURES;
    }

    public String getBenchNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_BENCH_NO_DEFAULT_FEATURES;
    }

    public String getBenchTargetKey() {
        return CargoConstants.PARAM_BENCH_TARGET;
    }

    public String getBenchManifestKey() {
        return CargoConstants.PARAM_BENCH_MANIFEST;
    }

    public String getBenchParallelKey() {
        return CargoConstants.PARAM_BENCH_PARALLEL;
    }

    public String getDocPackageKey() {
        return CargoConstants.PARAM_DOC_PACKAGE;
    }

    public String getDocReleaseKey() {
        return CargoConstants.PARAM_DOC_RELEASE;
    }

    public String getDocNoDependenciesKey() {
        return CargoConstants.PARAM_DOC_NO_DEPS;
    }

    public String getDocFeaturesKey() {
        return CargoConstants.PARAM_DOC_FEATURES;
    }

    public String getDocNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_DOC_NO_DEFAULT_FEATURES;
    }

    public String getDocTargetKey() {
        return CargoConstants.PARAM_DOC_TARGET;
    }

    public String getDocManifestKey() {
        return CargoConstants.PARAM_DOC_MANIFEST;
    }

    public String getDocParallelKey() {
        return CargoConstants.PARAM_DOC_PARALLEL;
    }

    public String getPackageNoVerifyKey() {
        return CargoConstants.PARAM_PACKAGE_NO_VERIFY;
    }

    public String getPackageNoMetadataKey() {
        return CargoConstants.PARAM_PACKAGE_NO_METADATA;
    }

    public String getPackageManifestKey() {
        return CargoConstants.PARAM_PACKAGE_MANIFEST;
    }

    public String getPublishHostKey() {
        return CargoConstants.PARAM_PUBLISH_HOST;
    }

    public String getPublishTokenKey() {
        return CargoConstants.PARAM_PUBLISH_TOKEN;
    }

    public String getPublishNoVerifyKey() {
        return CargoConstants.PARAM_PUBLISH_NO_VERIFY;
    }

    public String getPublishManifestKey() {
        return CargoConstants.PARAM_PUBLISH_MANIFEST;
    }

    public String getRustcOptsKey() {
        return CargoConstants.PARAM_RUSTC_OPTS;
    }

    public String getRustcPackageKey() {
        return CargoConstants.PARAM_RUSTC_PACKAGE;
    }

    public String getRustcTypeKey() {
        return CargoConstants.PARAM_RUSTC_TYPE;
    }

    public String getRustcTypeNameKey() {
        return CargoConstants.PARAM_RUSTC_TYPE_NAME;
    }

    public String getRustcReleaseKey() {
        return CargoConstants.PARAM_RUSTC_RELEASE;
    }

    public String getRustcFeaturesKey() {
        return CargoConstants.PARAM_RUSTC_FEATURES;
    }

    public String getRustcNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_RUSTC_NO_DEFAULT_FEATURES;
    }

    public String getRustcTargetKey() {
        return CargoConstants.PARAM_RUSTC_TARGET;
    }

    public String getRustcManifestKey() {
        return CargoConstants.PARAM_RUSTC_MANIFEST;
    }

    public String getRustcParallelKey() {
        return CargoConstants.PARAM_RUSTC_PARALLEL;
    }

    public String getLoginTokenKey() {
        return CargoConstants.PARAM_LOGIN_TOKEN;
    }

    public String getLoginHostKey() {
        return CargoConstants.PARAM_LOGIN_HOST;
    }

    public String getUpdatePackageKey() {
        return CargoConstants.PARAM_UPDATE_PACKAGE;
    }

    public String getUpdatePreciseKey() {
        return CargoConstants.PARAM_UPDATE_PRECISE;
    }

    public String getUpdateAggressiveKey() {
        return CargoConstants.PARAM_UPDATE_AGGRESSIVE;
    }

    public String getUpdateManifestKey() {
        return CargoConstants.PARAM_UPDATE_MANIFEST;
    }

    public String getRustDocOptionsKey() {
        return CargoConstants.PARAM_RUSTDOC_OPTS;
    }

    public String getRustDocPackageKey() {
        return CargoConstants.PARAM_RUSTDOC_PACKAGE;
    }

    public String getRustDocTypeKey() {
        return CargoConstants.PARAM_RUSTDOC_TYPE;
    }

    public String getRustDocTypeNameKey() {
        return CargoConstants.PARAM_RUSTDOC_TYPE_NAME;
    }

    public String getRustDocReleaseKey() {
        return CargoConstants.PARAM_RUSTDOC_RELEASE;
    }

    public String getRustDocFeaturesKey() {
        return CargoConstants.PARAM_RUSTDOC_FEATURES;
    }

    public String getRustDocNoDefaultFeaturesKey() {
        return CargoConstants.PARAM_RUSTDOC_NO_DEFAULT_FEATURES;
    }

    public String getRustDocTargetKey() {
        return CargoConstants.PARAM_RUSTDOC_TARGET;
    }

    public String getRustDocManifestKey() {
        return CargoConstants.PARAM_RUSTDOC_MANIFEST;
    }

    public String getRustDocParallelKey() {
        return CargoConstants.PARAM_RUSTDOC_PARALLEL;
    }

    public String getYankCrateKey() {
        return CargoConstants.PARAM_YANK_CRATE;
    }

    public String getYankVersionKey() {
        return CargoConstants.PARAM_YANK_VERSION;
    }

    public String getYankUndoKey() {
        return CargoConstants.PARAM_YANK_UNDO;
    }

    public String getYankIndexKey() {
        return CargoConstants.PARAM_YANK_INDEX;
    }

    public String getYankTokenKey() {
        return CargoConstants.PARAM_YANK_TOKEN;
    }
}