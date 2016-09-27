/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

/**
 * Cargo runner constants.
 */
interface CargoConstants {
    companion object {
        val RUNNER_TYPE = "cargo"
        val RUNNER_DISPLAY_NAME = "cargo"
        val RUNNER_DESCRIPTION = "Provides cargo support for rust projects"
        val CONFIG_PATH = RUNNER_TYPE + "_Path"

        val COMMAND_BENCH = "bench"
        val COMMAND_BUILD = "build"
        val COMMAND_CLEAN = "clean"
        val COMMAND_DOC = "doc"
        val COMMAND_LOGIN = "login"
        val COMMAND_PACKAGE = "package"
        val COMMAND_PUBLISH = "publish"
        val COMMAND_RUN = "run"
        val COMMAND_RUSTC = "rustc"
        val COMMAND_RUSTDOC = "rustdoc"
        val COMMAND_TEST = "test"
        val COMMAND_UPDATE = "update"
        val COMMAND_YANK = "yank"

        val PARAM_COMMAND = "cargo-command"
        val PARAM_VERBOSITY = "cargo-verbosity"

        val PARAM_BUILD_PACKAGE = "cargo-build-package"
        val PARAM_BUILD_TYPE = "cargo-build-type"
        val PARAM_BUILD_TYPE_NAME = "cargo-build-type-name"
        val PARAM_BUILD_FEATURES = "cargo-build-features"
        val PARAM_BUILD_NO_DEFAULT_FEATURES = "cargo-build-no-default-features"
        val PARAM_BUILD_RELEASE = "cargo-build-release"
        val PARAM_BUILD_TARGET = "cargo-build-target"
        val PARAM_BUILD_MANIFEST = "cargo-build-manifest"
        val PARAM_BUILD_PARALLEL = "cargo-build-parallel"

        val PARAM_CLEAN_PACKAGE = "cargo-clean-package"
        val PARAM_CLEAN_RELEASE = "cargo-clean-release"
        val PARAM_CLEAN_TARGET = "cargo clean-target"
        val PARAM_CLEAN_MANIFEST = "cargo-clean-manifest"

        val PARAM_TEST_ARGUMENTS = "cargo-test-arguments"
        val PARAM_TEST_PACKAGE = "cargo-test-package"
        val PARAM_TEST_TYPE = "cargo-test-type"
        val PARAM_TEST_TYPE_NAME = "cargo-test-type-name"
        val PARAM_TEST_RELEASE = "cargo-test-release"
        val PARAM_TEST_NO_RUN = "cargo-test-no-run"
        val PARAM_TEST_FEATURES = "cargo-test-features"
        val PARAM_TEST_NO_DEFAULT_FEATURES = "cargo-test-no-default-features"
        val PARAM_TEST_NO_FAIL_FAST = "cargo-test-no-fail-fast"
        val PARAM_TEST_TARGET = "cargo-test-target"
        val PARAM_TEST_MANIFEST = "cargo-test-manifest"
        val PARAM_TEST_PARALLEL = "cargo-test-parallel"

        val PARAM_RUN_ARGUMENTS = "cargo-run-arguments"
        val PARAM_RUN_TYPE = "cargo-run-type"
        val PARAM_RUN_TYPE_NAME = "cargo-run-type-name"
        val PARAM_RUN_RELEASE = "cargo-run-release"
        val PARAM_RUN_FEATURES = "cargo-run-features"
        val PARAM_RUN_NO_DEFAULT_FEATURES = "cargo-run-no-default-features"
        val PARAM_RUN_TARGET = "cargo-run-target"
        val PARAM_RUN_MANIFEST = "cargo-run-manifest"
        val PARAM_RUN_PARALLEL = "cargo-run-parallel"

        val PARAM_BENCH_ARGUMENTS = "cargo-bench-arguments"
        val PARAM_BENCH_PACKAGE = "cargo-bench-package"
        val PARAM_BENCH_TYPE = "cargo-bench-type"
        val PARAM_BENCH_TYPE_NAME = "cargo-bench-type-name"
        val PARAM_BENCH_RELEASE = "cargo-bench-release"
        val PARAM_BENCH_NO_RUN = "cargo-bench-no-run"
        val PARAM_BENCH_FEATURES = "cargo-bench-features"
        val PARAM_BENCH_NO_DEFAULT_FEATURES = "cargo-bench-no-default-features"
        val PARAM_BENCH_TARGET = "cargo-bench-target"
        val PARAM_BENCH_MANIFEST = "cargo-bench-manifest"
        val PARAM_BENCH_PARALLEL = "cargo-bench-parallel"

        val PARAM_DOC_PACKAGE = "cargo-doc-package"
        val PARAM_DOC_RELEASE = "cargo-doc-release"
        val PARAM_DOC_NO_DEPS = "cargo-doc-no-deps"
        val PARAM_DOC_FEATURES = "cargo-doc-features"
        val PARAM_DOC_NO_DEFAULT_FEATURES = "cargo-doc-no-default-features"
        val PARAM_DOC_TARGET = "cargo-doc-target"
        val PARAM_DOC_MANIFEST = "cargo-doc-manifest"
        val PARAM_DOC_PARALLEL = "cargo-doc-parallel"

        val PARAM_PACKAGE_NO_VERIFY = "cargo-package-no-verify"
        val PARAM_PACKAGE_NO_METADATA = "cargo-package-no-metadata"
        val PARAM_PACKAGE_MANIFEST = "cargo-package-manifest"

        val PARAM_PUBLISH_HOST = "cargo-publish-host"
        val PARAM_PUBLISH_TOKEN = "cargo-publish-token"
        val PARAM_PUBLISH_NO_VERIFY = "cargo-publish-no-verify"
        val PARAM_PUBLISH_MANIFEST = "cargo-publish-manifest"

        val PARAM_RUSTC_OPTS = "cargo-rustc-opts"
        val PARAM_RUSTC_PACKAGE = "cargo-rustc-package"
        val PARAM_RUSTC_TYPE = "cargo-rustc-type"
        val PARAM_RUSTC_TYPE_NAME = "cargo-rustc-type-name"
        val PARAM_RUSTC_RELEASE = "cargo-rustc-release"
        val PARAM_RUSTC_FEATURES = "cargo-rustc-features"
        val PARAM_RUSTC_NO_DEFAULT_FEATURES = "cargo-rustc-no-default-features"
        val PARAM_RUSTC_TARGET = "cargo-rustc-target"
        val PARAM_RUSTC_MANIFEST = "cargo-rustc-manifest"
        val PARAM_RUSTC_PARALLEL = "cargo-rustc-parallel"

        val PARAM_LOGIN_TOKEN = "cargo-login-token"
        val PARAM_LOGIN_HOST = "cargo-login-host"

        val PARAM_UPDATE_PACKAGE = "cargo-update-package"
        val PARAM_UPDATE_PRECISE = "cargo-update-precise"
        val PARAM_UPDATE_AGGRESSIVE = "cargo-update-aggressive"
        val PARAM_UPDATE_MANIFEST = "cargo-update-manifest"

        val PARAM_RUSTDOC_OPTS = "cargo-rustdoc-opts"
        val PARAM_RUSTDOC_PACKAGE = "cargo-rustdoc-package"
        val PARAM_RUSTDOC_TYPE = "cargo-rustdoc-type"
        val PARAM_RUSTDOC_TYPE_NAME = "cargo-rustdoc-type-name"
        val PARAM_RUSTDOC_RELEASE = "cargo-rustdoc-release"
        val PARAM_RUSTDOC_FEATURES = "cargo-rustdoc-features"
        val PARAM_RUSTDOC_NO_DEFAULT_FEATURES = "cargo-rustdoc-no-default-features"
        val PARAM_RUSTDOC_TARGET = "cargo-rustdoc-target"
        val PARAM_RUSTDOC_MANIFEST = "cargo-rustdoc-manifest"
        val PARAM_RUSTDOC_PARALLEL = "cargo-rustdoc-parallel"

        val PARAM_YANK_CRATE = "cargo-yank-crate"
        val PARAM_YANK_VERSION = "cargo-yank-version"
        val PARAM_YANK_UNDO = "cargo-yank-undo"
        val PARAM_YANK_INDEX = "cargo-yank-index"
        val PARAM_YANK_TOKEN = "cargo-yank-token"
    }
}
