/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.controllers.BasePropertiesBean
import jetbrains.buildServer.rust.commands.CommandType
import jetbrains.buildServer.rust.commands.cargo.*
import jetbrains.buildServer.util.StringUtil

/**
 * Provides parameters for cargo runner.
 */
class CargoParametersProvider {

    val types: List<CommandType> = listOf(
            BenchCommandType(),
            BuildCommandType(),
            CleanCommandType(),
            DocCommandType(),
            LoginCommandType(),
            PackageCommandType(),
            PublishCommandType(),
            RunCommandType(),
            RustcCommandType(),
            RustDocCommandType(),
            TestCommandType(),
            UpdateCommandType(),
            YankCommandType()
    )

    val commandKey: String
        get() = CargoConstants.PARAM_COMMAND

    val verbosityKey: String
        get() = CargoConstants.PARAM_VERBOSITY

    val toolchainKey: String
        get() = CargoConstants.PARAM_TOOLCHAIN

    val buildPackageKey: String
        get() = CargoConstants.PARAM_BUILD_PACKAGE

    val buildTypeKey: String
        get() = CargoConstants.PARAM_BUILD_TYPE

    val buildTypeNameKey: String
        get() = CargoConstants.PARAM_BUILD_TYPE_NAME

    val buildFeaturesKey: String
        get() = CargoConstants.PARAM_BUILD_FEATURES

    val buildNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_BUILD_NO_DEFAULT_FEATURES

    val buildReleaseKey: String
        get() = CargoConstants.PARAM_BUILD_RELEASE

    val buildTargetKey: String
        get() = CargoConstants.PARAM_BUILD_TARGET

    val buildManifestKey: String
        get() = CargoConstants.PARAM_BUILD_MANIFEST

    val buildParallelKey: String
        get() = CargoConstants.PARAM_BUILD_PARALLEL

    val cleanPackageKey: String
        get() = CargoConstants.PARAM_CLEAN_PACKAGE

    val cleanReleaseKey: String
        get() = CargoConstants.PARAM_CLEAN_RELEASE

    val cleanTargetKey: String
        get() = CargoConstants.PARAM_CLEAN_TARGET

    val cleanManifestKey: String
        get() = CargoConstants.PARAM_CLEAN_MANIFEST

    val testArgumentsKey: String
        get() = CargoConstants.PARAM_TEST_ARGUMENTS

    val testPackageKey: String
        get() = CargoConstants.PARAM_TEST_PACKAGE

    val testTypeKey: String
        get() = CargoConstants.PARAM_TEST_TYPE

    val testTypeNameKey: String
        get() = CargoConstants.PARAM_TEST_TYPE_NAME

    val testReleaseKey: String
        get() = CargoConstants.PARAM_TEST_RELEASE

    val testNoRunKey: String
        get() = CargoConstants.PARAM_TEST_NO_RUN

    val testFeaturesKey: String
        get() = CargoConstants.PARAM_TEST_FEATURES

    val testNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES

    val testNoFailFastKey: String
        get() = CargoConstants.PARAM_TEST_NO_FAIL_FAST

    val testTargetKey: String
        get() = CargoConstants.PARAM_TEST_TARGET

    val testManifestKey: String
        get() = CargoConstants.PARAM_TEST_MANIFEST

    val testParallelKey: String
        get() = CargoConstants.PARAM_TEST_PARALLEL

    val runArgumentsKey: String
        get() = CargoConstants.PARAM_RUN_ARGUMENTS

    val runTypeKey: String
        get() = CargoConstants.PARAM_RUN_TYPE

    val runTypeNameKey: String
        get() = CargoConstants.PARAM_RUN_TYPE_NAME

    val runReleaseKey: String
        get() = CargoConstants.PARAM_RUN_RELEASE

    val runFeaturesKey: String
        get() = CargoConstants.PARAM_RUN_FEATURES

    val runNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_RUN_NO_DEFAULT_FEATURES

    val runTargetKey: String
        get() = CargoConstants.PARAM_RUN_TARGET

    val runManifestKey: String
        get() = CargoConstants.PARAM_RUN_MANIFEST

    val runParallelKey: String
        get() = CargoConstants.PARAM_RUN_PARALLEL

    val benchArgumentsKey: String
        get() = CargoConstants.PARAM_BENCH_ARGUMENTS

    val benchPackageKey: String
        get() = CargoConstants.PARAM_BENCH_PACKAGE

    val benchTypeKey: String
        get() = CargoConstants.PARAM_BENCH_TYPE

    val benchTypeNameKey: String
        get() = CargoConstants.PARAM_BENCH_TYPE_NAME

    val benchReleaseKey: String
        get() = CargoConstants.PARAM_BENCH_RELEASE

    val benchNoRunKey: String
        get() = CargoConstants.PARAM_BENCH_NO_RUN

    val benchFeaturesKey: String
        get() = CargoConstants.PARAM_BENCH_FEATURES

    val benchNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_BENCH_NO_DEFAULT_FEATURES

    val benchTargetKey: String
        get() = CargoConstants.PARAM_BENCH_TARGET

    val benchManifestKey: String
        get() = CargoConstants.PARAM_BENCH_MANIFEST

    val benchParallelKey: String
        get() = CargoConstants.PARAM_BENCH_PARALLEL

    val docPackageKey: String
        get() = CargoConstants.PARAM_DOC_PACKAGE

    val docReleaseKey: String
        get() = CargoConstants.PARAM_DOC_RELEASE

    val docNoDependenciesKey: String
        get() = CargoConstants.PARAM_DOC_NO_DEPS

    val docFeaturesKey: String
        get() = CargoConstants.PARAM_DOC_FEATURES

    val docNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_DOC_NO_DEFAULT_FEATURES

    val docTargetKey: String
        get() = CargoConstants.PARAM_DOC_TARGET

    val docManifestKey: String
        get() = CargoConstants.PARAM_DOC_MANIFEST

    val docParallelKey: String
        get() = CargoConstants.PARAM_DOC_PARALLEL

    val packageNoVerifyKey: String
        get() = CargoConstants.PARAM_PACKAGE_NO_VERIFY

    val packageNoMetadataKey: String
        get() = CargoConstants.PARAM_PACKAGE_NO_METADATA

    val packageManifestKey: String
        get() = CargoConstants.PARAM_PACKAGE_MANIFEST

    val publishHostKey: String
        get() = CargoConstants.PARAM_PUBLISH_HOST

    val publishTokenKey: String
        get() = CargoConstants.PARAM_PUBLISH_TOKEN

    val publishTokenKeySecure: String
        get() = CargoConstants.PARAM_PUBLISH_TOKEN_SECURE

    val publishNoVerifyKey: String
        get() = CargoConstants.PARAM_PUBLISH_NO_VERIFY

    val publishManifestKey: String
        get() = CargoConstants.PARAM_PUBLISH_MANIFEST

    val rustcOptsKey: String
        get() = CargoConstants.PARAM_RUSTC_OPTS

    val rustcPackageKey: String
        get() = CargoConstants.PARAM_RUSTC_PACKAGE

    val rustcTypeKey: String
        get() = CargoConstants.PARAM_RUSTC_TYPE

    val rustcTypeNameKey: String
        get() = CargoConstants.PARAM_RUSTC_TYPE_NAME

    val rustcReleaseKey: String
        get() = CargoConstants.PARAM_RUSTC_RELEASE

    val rustcFeaturesKey: String
        get() = CargoConstants.PARAM_RUSTC_FEATURES

    val rustcNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_RUSTC_NO_DEFAULT_FEATURES

    val rustcTargetKey: String
        get() = CargoConstants.PARAM_RUSTC_TARGET

    val rustcManifestKey: String
        get() = CargoConstants.PARAM_RUSTC_MANIFEST

    val rustcParallelKey: String
        get() = CargoConstants.PARAM_RUSTC_PARALLEL

    val loginTokenKey: String
        get() = CargoConstants.PARAM_LOGIN_TOKEN

    val loginTokenKeySecure: String
        get() = CargoConstants.PARAM_LOGIN_TOKEN_SECURE

    val loginHostKey: String
        get() = CargoConstants.PARAM_LOGIN_HOST

    val updatePackageKey: String
        get() = CargoConstants.PARAM_UPDATE_PACKAGE

    val updatePreciseKey: String
        get() = CargoConstants.PARAM_UPDATE_PRECISE

    val updateAggressiveKey: String
        get() = CargoConstants.PARAM_UPDATE_AGGRESSIVE

    val updateManifestKey: String
        get() = CargoConstants.PARAM_UPDATE_MANIFEST

    val rustDocOptionsKey: String
        get() = CargoConstants.PARAM_RUSTDOC_OPTS

    val rustDocPackageKey: String
        get() = CargoConstants.PARAM_RUSTDOC_PACKAGE

    val rustDocTypeKey: String
        get() = CargoConstants.PARAM_RUSTDOC_TYPE

    val rustDocTypeNameKey: String
        get() = CargoConstants.PARAM_RUSTDOC_TYPE_NAME

    val rustDocReleaseKey: String
        get() = CargoConstants.PARAM_RUSTDOC_RELEASE

    val rustDocFeaturesKey: String
        get() = CargoConstants.PARAM_RUSTDOC_FEATURES

    val rustDocNoDefaultFeaturesKey: String
        get() = CargoConstants.PARAM_RUSTDOC_NO_DEFAULT_FEATURES

    val rustDocTargetKey: String
        get() = CargoConstants.PARAM_RUSTDOC_TARGET

    val rustDocManifestKey: String
        get() = CargoConstants.PARAM_RUSTDOC_MANIFEST

    val rustDocParallelKey: String
        get() = CargoConstants.PARAM_RUSTDOC_PARALLEL

    val yankCrateKey: String
        get() = CargoConstants.PARAM_YANK_CRATE

    val yankVersionKey: String
        get() = CargoConstants.PARAM_YANK_VERSION

    val yankUndoKey: String
        get() = CargoConstants.PARAM_YANK_UNDO

    val yankIndexKey: String
        get() = CargoConstants.PARAM_YANK_INDEX

    val yankTokenKey: String
        get() = CargoConstants.PARAM_YANK_TOKEN

    val yankTokenKeySecure: String
        get() = CargoConstants.PARAM_YANK_TOKEN_SECURE

    companion object {
        @JvmStatic
        fun replaceWithNewParameter(propertiesBean: BasePropertiesBean, oldParameterName: String, newParameterName: String) {
            if (!propertiesBean.properties.containsKey(newParameterName) && propertiesBean.properties.containsKey(oldParameterName)) {
                propertiesBean.properties[newParameterName] = propertiesBean.properties[oldParameterName]
                propertiesBean.properties.remove(oldParameterName)
            }
        }
    }
}