/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;

/**
 * Default logger implementation.
 */
public class CargoDefaultLogger implements CargoLogger {

    private final BuildProgressLogger myLogger;

    public CargoDefaultLogger(@NotNull final BuildProgressLogger logger){
        myLogger = logger;
    }

    @Override
    public void onEnter(@NotNull final String text) {
        myLogger.message(text);
    }

    @Override
    public void processLine(@NotNull final String text) {
        myLogger.message(text);
    }

    @Override
    public void processError(@NotNull final String text) {
        myLogger.error(text);
    }

    @Override
    public void onLeave() {
    }

    @Override
    public boolean canChangeState(CargoState state, String text) {
        return true;
    }
}
