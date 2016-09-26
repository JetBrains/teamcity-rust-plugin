/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Cargo states.
 * Reference: https://github.com/rust-lang/cargo/blob/master/tests/cargotest/support/mod.rs
 */
public enum CargoState {
    Running("Running"),
    Compiling("Compiling"),
    Error("error:"),
    Warning("warning:"),
    Documenting("Documenting"),
    Fresh("Fresh"),
    Updating("Updating"),
    Adding("Adding"),
    Removing("Removing"),
    DocTests("Doc-tests"),
    Packaging("Packaging"),
    Downloading("Downloading"),
    Uploading("Uploading"),
    Verifying("Verifying"),
    Archiving("Archiving"),
    Installing("Installing"),
    Replacing("Replacing"),

    Default("Default"),
    Testing("Testing");

    private String myName;

    CargoState(@NotNull final String name) {
        myName = name;
    }

    @Override
    public String toString() {
        return myName;
    }

    private static final Map<String, CargoState> STATES = new HashMap<String, CargoState>();

    static {
        for (CargoState state : values()) {
            STATES.put(state.myName, state);
        }
    }

    public static CargoState get(String value) {
        return STATES.get(value);
    }
}
