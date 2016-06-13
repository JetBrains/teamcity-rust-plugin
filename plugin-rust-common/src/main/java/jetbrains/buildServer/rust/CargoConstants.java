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
}
