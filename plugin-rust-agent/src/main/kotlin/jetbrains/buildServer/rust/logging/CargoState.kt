/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import java.util.HashMap

/**
 * Cargo states.
 * Reference: https://github.com/rust-lang/cargo/blob/master/tests/cargotest/support/mod.rs
 */
enum class CargoState constructor(private val myName: String) {
    Running("Running"),
    Compiling("Compiling"),
    Created("Created"),
    Finished("Finished"),
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
    Unpacking("Unpacking"),

    Default("Default"),
    Testing("Testing"),
    ErrorDetails("ErrorDetails");

    override fun toString(): String {
        return myName
    }

    companion object {

        private val STATES = HashMap<String, CargoState>()

        init {
            for (state in values()) {
                STATES.put(state.myName, state)
            }
        }

        operator fun get(value: String): CargoState? {
            return STATES[value]
        }
    }
}
