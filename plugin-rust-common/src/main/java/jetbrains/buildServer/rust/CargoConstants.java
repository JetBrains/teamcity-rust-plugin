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
    String COMMAND_LOGIN = "login";
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
    String PARAM_TEST_NO_RUN = "cargo-test-no-run";
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

    String PARAM_BENCH_ARGUMENTS = "cargo-bench-arguments";
    String PARAM_BENCH_PACKAGE = "cargo-bench-package";
    String PARAM_BENCH_TYPE = "cargo-bench-type";
    String PARAM_BENCH_TYPE_NAME = "cargo-bench-type-name";
    String PARAM_BENCH_RELEASE = "cargo-bench-release";
    String PARAM_BENCH_NO_RUN = "cargo-bench-no-run";
    String PARAM_BENCH_FEATURES = "cargo-bench-features";
    String PARAM_BENCH_NO_DEFAULT_FEATURES = "cargo-bench-no-default-features";
    String PARAM_BENCH_TARGET = "cargo-bench-target";
    String PARAM_BENCH_MANIFEST = "cargo-bench-manifest";
    String PARAM_BENCH_PARALLEL = "cargo-bench-parallel";

    String PARAM_DOC_PACKAGE = "cargo-doc-package";
    String PARAM_DOC_RELEASE = "cargo-doc-release";
    String PARAM_DOC_NO_DEPS = "cargo-doc-no-deps";
    String PARAM_DOC_FEATURES = "cargo-doc-features";
    String PARAM_DOC_NO_DEFAULT_FEATURES = "cargo-doc-no-default-features";
    String PARAM_DOC_TARGET = "cargo-doc-target";
    String PARAM_DOC_MANIFEST = "cargo-doc-manifest";
    String PARAM_DOC_PARALLEL = "cargo-doc-parallel";

    String PARAM_PACKAGE_NO_VERIFY = "cargo-package-no-verify";
    String PARAM_PACKAGE_NO_METADATA = "cargo-package-no-metadata";
    String PARAM_PACKAGE_MANIFEST = "cargo-package-manifest";

    String PARAM_PUBLISH_HOST = "cargo-publish-host";
    String PARAM_PUBLISH_TOKEN = "cargo-publish-token";
    String PARAM_PUBLISH_NO_VERIFY = "cargo-publish-no-verify";
    String PARAM_PUBLISH_MANIFEST = "cargo-publish-manifest";

    String PARAM_RUSTC_OPTS = "cargo-rustc-opts";
    String PARAM_RUSTC_PACKAGE = "cargo-rustc-package";
    String PARAM_RUSTC_TYPE = "cargo-rustc-type";
    String PARAM_RUSTC_TYPE_NAME = "cargo-rustc-type-name";
    String PARAM_RUSTC_RELEASE = "cargo-rustc-release";
    String PARAM_RUSTC_FEATURES = "cargo-rustc-features";
    String PARAM_RUSTC_NO_DEFAULT_FEATURES = "cargo-rustc-no-default-features";
    String PARAM_RUSTC_TARGET = "cargo-rustc-target";
    String PARAM_RUSTC_MANIFEST = "cargo-rustc-manifest";
    String PARAM_RUSTC_PARALLEL = "cargo-rustc-parallel";

    String PARAM_LOGIN_TOKEN = "cargo-login-token";
    String PARAM_LOGIN_HOST = "cargo-login-host";

    String PARAM_UPDATE_PACKAGE = "cargo-update-package";
    String PARAM_UPDATE_PRECISE = "cargo-update-precise";
    String PARAM_UPDATE_AGGRESSIVE = "cargo-update-aggressive";
    String PARAM_UPDATE_MANIFEST = "cargo-update-manifest";
    
    String PARAM_RUSTDOC_OPTS = "cargo-rustdoc-opts";
    String PARAM_RUSTDOC_PACKAGE = "cargo-rustdoc-package";
    String PARAM_RUSTDOC_TYPE = "cargo-rustdoc-type";
    String PARAM_RUSTDOC_TYPE_NAME = "cargo-rustdoc-type-name";
    String PARAM_RUSTDOC_RELEASE = "cargo-rustdoc-release";
    String PARAM_RUSTDOC_FEATURES = "cargo-rustdoc-features";
    String PARAM_RUSTDOC_NO_DEFAULT_FEATURES = "cargo-rustdoc-no-default-features";
    String PARAM_RUSTDOC_TARGET = "cargo-rustdoc-target";
    String PARAM_RUSTDOC_MANIFEST = "cargo-rustdoc-manifest";
    String PARAM_RUSTDOC_PARALLEL = "cargo-rustdoc-parallel";
}
