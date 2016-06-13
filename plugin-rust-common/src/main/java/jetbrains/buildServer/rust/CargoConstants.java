/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust;

/**
 * Cargo runner constants.
 */
public interface CargoConstants {
    String RUNNER_TYPE = "cargo";
    String RUNNER_DISPLAY_NAME = "cargo";
    String RUNNER_DESCRIPTION = "Provides cargo support for rust projects";

    String COMMAND_BENCH = "bench";
    String COMMAND_BUILD = "build";
    String COMMAND_CLEAN = "clean";
    String COMMAND_DOC = "doc";
    String COMMAND_LOGIN = "lgin";
    String COMMAND_PACKAGE = "package";
    String COMMAND_PUBLISH = "publish";
    String COMMAND_RUN = "run";
    String COMMAND_RUSTC = "rustc";
    String COMMAND_RUSTDOC = "rustdoc";
    String COMMAND_TEST = "test";
    String COMMAND_UPDATE = "update";
    String COMMAND_YANK = "yank";

    String PARAM_COMMAND = "cargo-command";
    String PARAM_VERBOSITY = "cargo-verbosity";

    String PARAM_BUILD_PACKAGE = "cargo-build-package";
    String PARAM_BUILD_TYPE = "cargo-build-type";
    String PARAM_BUILD_TYPE_NAME = "cargo-build-type-name";
    String PARAM_BUILD_FEATURES = "cargo-build-features";
    String PARAM_BUILD_NO_DEFAULT_FEATURES = "cargo-build-no-default-features";
    String PARAM_BUILD_RELEASE = "cargo-build-release";
    String PARAM_BUILD_TARGET = "cargo-build-target";
    String PARAM_BUILD_MANIFEST = "cargo-build-manifest";
    String PARAM_BUILD_PARALLEL = "cargo-build-parallel";

    String PARAM_CLEAN_PACKAGE = "cargo-clean-package";
    String PARAM_CLEAN_RELEASE = "cargo-clean-release";
    String PARAM_CLEAN_TARGET = "cargo clean-target";
    String PARAM_CLEAN_MANIFEST = "cargo-clean-manifest";

    String PARAM_TEST_ARGUMENTS = "cargo-test-arguments";
    String PARAM_TEST_PACKAGE = "cargo-test-package";
    String PARAM_TEST_TYPE = "cargo-test-type";
    String PARAM_TEST_TYPE_NAME = "cargo-test-type-name";
    String PARAM_TEST_RELEASE = "cargo-test-release";
    String PARAM_TEST_NO_RUN_TESTS = "cargo-test-no-run-tests";
    String PARAM_TEST_FEATURES = "cargo-test-features";
    String PARAM_TEST_NO_DEFAULT_FEATURES = "cargo-test-no-default-features";
    String PARAM_TEST_NO_FAIL_FAST = "cargo-test-no-fail-fast";
    String PARAM_TEST_TARGET = "cargo-test-target";
    String PARAM_TEST_MANIFEST = "cargo-test-manifest";
    String PARAM_TEST_PARALLEL = "cargo-test-parallel";
    
    String PARAM_RUN_ARGUMENTS = "cargo-run-arguments";
    String PARAM_RUN_TYPE = "cargo-run-type";
    String PARAM_RUN_TYPE_NAME = "cargo-run-type-name";
    String PARAM_RUN_RELEASE = "cargo-run-release";
    String PARAM_RUN_FEATURES = "cargo-run-features";
    String PARAM_RUN_NO_DEFAULT_FEATURES = "cargo-run-no-default-features";
    String PARAM_RUN_TARGET = "cargo-run-target";
    String PARAM_RUN_MANIFEST = "cargo-run-manifest";
    String PARAM_RUN_PARALLEL = "cargo-run-parallel";
}
