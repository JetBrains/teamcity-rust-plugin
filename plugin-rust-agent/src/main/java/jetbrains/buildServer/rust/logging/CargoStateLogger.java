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
 * State-based logger.
 */
public class CargoStateLogger extends CargoDefaultLogger {
    private static final String MESSAGE_FORMAT = "##teamcity[message text='%s']";
    private final BuildProgressLogger myLogger;
    private final CargoState myState;

    public CargoStateLogger(@NotNull final BuildProgressLogger logger, CargoState state) {
        super(logger);
        myLogger = logger;
        myState = state;
    }

    @Override
    public void onEnter(String text) {
        final String message = myState + " " + text;
        myLogger.message(String.format(MESSAGE_FORMAT, message));
    }

    @Override
    public void processLine(String text) {
        myLogger.message(String.format(MESSAGE_FORMAT, text));
    }

    @Override
    public void onLeave() {
    }
}
