/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

/**
 * Cargo runner constants.
 */
object CargoConstants {
    const val RUNNER_TYPE = "cargo"
    const val RUNNER_DISPLAY_NAME = "Cargo"
    const val RUNNER_DESCRIPTION = "Provides cargo support for rust projects"
    const val CARGO_CONFIG_NAME = "Cargo"
    const val CARGO_CONFIG_PATH = CARGO_CONFIG_NAME + "_Path"
    const val CARGO_EXECUTABLE_NAME = "cargo"
    const val RUSTC_CONFIG_NAME = "Rustc"
    const val RUSTC_CONFIG_PATH = RUSTC_CONFIG_NAME + "_Path"
    const val RUSTC_EXECUTABLE_NAME = "rustc"
    const val RUSTUP_CONFIG_NAME = "Rustup"
    const val RUSTUP_CONFIG_PATH = RUSTUP_CONFIG_NAME + "_Path"
    const val RUSTUP_EXECUTABLE_NAME = "rustup"
    const val PROJECT_FILE = "Cargo.toml"

    const val ENV_CARGO_HOME = "CARGO_HOME"
    const val ENV_RUSTUP_HOME = "RUSTUP_HOME"

    const val COMMAND_BENCH = "bench"
    const val COMMAND_BUILD = "build"
    const val COMMAND_CLEAN = "clean"
    const val COMMAND_CLIPPY = "clippy"
    const val COMMAND_DOC = "doc"
    const val COMMAND_LOGIN = "login"
    const val COMMAND_PACKAGE = "package"
    const val COMMAND_PUBLISH = "publish"
    const val COMMAND_RUN = "run"
    const val COMMAND_RUSTC = "rustc"
    const val COMMAND_RUSTDOC = "rustdoc"
    const val COMMAND_TEST = "test"
    const val COMMAND_UPDATE = "update"
    const val COMMAND_YANK = "yank"
    const val COMMAND_CUSTOM_CRATE = "custom-crate"

    const val PARAM_COMMAND = "cargo-command"
    const val PARAM_VERBOSITY = "cargo-verbosity"
    const val PARAM_TOOLCHAIN = "cargo-toolchain"
    const val PARAM_ADDITIONAL_ARGUMENTS = "cargo-additional-arguments"

    const val PARAM_BUILD_PACKAGE = "cargo-build-package"
    const val PARAM_BUILD_TYPE = "cargo-build-type"
    const val PARAM_BUILD_TYPE_NAME = "cargo-build-type-name"
    const val PARAM_BUILD_FEATURES = "cargo-build-features"
    const val PARAM_BUILD_NO_DEFAULT_FEATURES = "cargo-build-no-default-features"
    const val PARAM_BUILD_RELEASE = "cargo-build-release"
    const val PARAM_BUILD_TARGET = "cargo-build-target"
    const val PARAM_BUILD_MANIFEST = "cargo-build-manifest"
    const val PARAM_BUILD_PARALLEL = "cargo-build-parallel"

    const val PARAM_CLEAN_PACKAGE = "cargo-clean-package"
    const val PARAM_CLEAN_RELEASE = "cargo-clean-release"
    const val PARAM_CLEAN_TARGET = "cargo clean-target"
    const val PARAM_CLEAN_MANIFEST = "cargo-clean-manifest"

    const val PARAM_CLIPPY_MANIFEST = "cargo-clippy-manifest"

    const val PARAM_TEST_ARGUMENTS = "cargo-test-arguments"
    const val PARAM_TEST_PACKAGE = "cargo-test-package"
    const val PARAM_TEST_TYPE = "cargo-test-type"
    const val PARAM_TEST_TYPE_NAME = "cargo-test-type-name"
    const val PARAM_TEST_RELEASE = "cargo-test-release"
    const val PARAM_TEST_NO_RUN = "cargo-test-no-run"
    const val PARAM_TEST_FEATURES = "cargo-test-features"
    const val PARAM_TEST_NO_DEFAULT_FEATURES = "cargo-test-no-default-features"
    const val PARAM_TEST_NO_FAIL_FAST = "cargo-test-no-fail-fast"
    const val PARAM_TEST_TARGET = "cargo-test-target"
    const val PARAM_TEST_MANIFEST = "cargo-test-manifest"
    const val PARAM_TEST_PARALLEL = "cargo-test-parallel"

    const val PARAM_RUN_ARGUMENTS = "cargo-run-arguments"
    const val PARAM_RUN_TYPE = "cargo-run-type"
    const val PARAM_RUN_TYPE_NAME = "cargo-run-type-name"
    const val PARAM_RUN_RELEASE = "cargo-run-release"
    const val PARAM_RUN_FEATURES = "cargo-run-features"
    const val PARAM_RUN_NO_DEFAULT_FEATURES = "cargo-run-no-default-features"
    const val PARAM_RUN_TARGET = "cargo-run-target"
    const val PARAM_RUN_MANIFEST = "cargo-run-manifest"
    const val PARAM_RUN_PARALLEL = "cargo-run-parallel"

    const val PARAM_BENCH_ARGUMENTS = "cargo-bench-arguments"
    const val PARAM_BENCH_PACKAGE = "cargo-bench-package"
    const val PARAM_BENCH_TYPE = "cargo-bench-type"
    const val PARAM_BENCH_TYPE_NAME = "cargo-bench-type-name"
    const val PARAM_BENCH_RELEASE = "cargo-bench-release"
    const val PARAM_BENCH_NO_RUN = "cargo-bench-no-run"
    const val PARAM_BENCH_FEATURES = "cargo-bench-features"
    const val PARAM_BENCH_NO_DEFAULT_FEATURES = "cargo-bench-no-default-features"
    const val PARAM_BENCH_TARGET = "cargo-bench-target"
    const val PARAM_BENCH_MANIFEST = "cargo-bench-manifest"
    const val PARAM_BENCH_PARALLEL = "cargo-bench-parallel"

    const val PARAM_DOC_PACKAGE = "cargo-doc-package"
    const val PARAM_DOC_RELEASE = "cargo-doc-release"
    const val PARAM_DOC_NO_DEPS = "cargo-doc-no-deps"
    const val PARAM_DOC_FEATURES = "cargo-doc-features"
    const val PARAM_DOC_NO_DEFAULT_FEATURES = "cargo-doc-no-default-features"
    const val PARAM_DOC_TARGET = "cargo-doc-target"
    const val PARAM_DOC_MANIFEST = "cargo-doc-manifest"
    const val PARAM_DOC_PARALLEL = "cargo-doc-parallel"

    const val PARAM_PACKAGE_NO_VERIFY = "cargo-package-no-verify"
    const val PARAM_PACKAGE_NO_METADATA = "cargo-package-no-metadata"
    const val PARAM_PACKAGE_MANIFEST = "cargo-package-manifest"

    const val PARAM_PUBLISH_HOST = "cargo-publish-host"
    const val PARAM_PUBLISH_TOKEN = "cargo-publish-token"
    const val PARAM_PUBLISH_TOKEN_SECURE = "secure:cargo-publish-token"
    const val PARAM_PUBLISH_NO_VERIFY = "cargo-publish-no-verify"
    const val PARAM_PUBLISH_MANIFEST = "cargo-publish-manifest"

    const val PARAM_RUSTC_OPTS = "cargo-rustc-opts"
    const val PARAM_RUSTC_PACKAGE = "cargo-rustc-package"
    const val PARAM_RUSTC_TYPE = "cargo-rustc-type"
    const val PARAM_RUSTC_TYPE_NAME = "cargo-rustc-type-name"
    const val PARAM_RUSTC_RELEASE = "cargo-rustc-release"
    const val PARAM_RUSTC_FEATURES = "cargo-rustc-features"
    const val PARAM_RUSTC_NO_DEFAULT_FEATURES = "cargo-rustc-no-default-features"
    const val PARAM_RUSTC_TARGET = "cargo-rustc-target"
    const val PARAM_RUSTC_MANIFEST = "cargo-rustc-manifest"
    const val PARAM_RUSTC_PARALLEL = "cargo-rustc-parallel"

    const val PARAM_LOGIN_TOKEN = "cargo-login-token"
    const val PARAM_LOGIN_TOKEN_SECURE = "secure:cargo-login-token"
    const val PARAM_LOGIN_HOST = "cargo-login-host"

    const val PARAM_UPDATE_PACKAGE = "cargo-update-package"
    const val PARAM_UPDATE_PRECISE = "cargo-update-precise"
    const val PARAM_UPDATE_AGGRESSIVE = "cargo-update-aggressive"
    const val PARAM_UPDATE_MANIFEST = "cargo-update-manifest"

    const val PARAM_RUSTDOC_OPTS = "cargo-rustdoc-opts"
    const val PARAM_RUSTDOC_PACKAGE = "cargo-rustdoc-package"
    const val PARAM_RUSTDOC_TYPE = "cargo-rustdoc-type"
    const val PARAM_RUSTDOC_TYPE_NAME = "cargo-rustdoc-type-name"
    const val PARAM_RUSTDOC_RELEASE = "cargo-rustdoc-release"
    const val PARAM_RUSTDOC_FEATURES = "cargo-rustdoc-features"
    const val PARAM_RUSTDOC_NO_DEFAULT_FEATURES = "cargo-rustdoc-no-default-features"
    const val PARAM_RUSTDOC_TARGET = "cargo-rustdoc-target"
    const val PARAM_RUSTDOC_MANIFEST = "cargo-rustdoc-manifest"
    const val PARAM_RUSTDOC_PARALLEL = "cargo-rustdoc-parallel"

    const val PARAM_YANK_CRATE = "cargo-yank-crate"
    const val PARAM_YANK_VERSION = "cargo-yank-version"
    const val PARAM_YANK_UNDO = "cargo-yank-undo"
    const val PARAM_YANK_INDEX = "cargo-yank-index"
    const val PARAM_YANK_TOKEN = "cargo-yank-token"
    const val PARAM_YANK_TOKEN_SECURE = "secure:cargo-yank-token"

    const val PARAM_CUSTOM_CRATE_COMMAND_NAME = "cargo-custom-crate-name"

    const val RUSTUP_DOWNLOADS_DIR = "downloads"
    const val RUSTUP_TMP_DIR = "tmp"
    const val RUSTUP_TOOLCHAINS_DIR = "toolchains"
    const val RUSTUP_HASHES_DIR = "update-hashes"

}
