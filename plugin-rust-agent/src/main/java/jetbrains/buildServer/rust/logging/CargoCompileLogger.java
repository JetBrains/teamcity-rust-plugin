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
 * Compiling logger.
 */
public class CargoCompileLogger extends CargoDefaultLogger {
    private static final String COMPILATION_STARTED = "##teamcity[compilationStarted compiler='rustc']";
    private static final String COMPILATION_FINISHED = "##teamcity[compilationFinished compiler='rustc']";
    private static final String COMPILATION_MESSAGE_FORMAT = "##teamcity[message text='Compiling %s']";
    private final BuildProgressLogger myLogger;
    private String myText;

    public CargoCompileLogger(@NotNull final BuildProgressLogger logger) {
        super(logger);
        myLogger = logger;
    }

    @Override
    public void onEnter(@NotNull final String text) {
        myText = text;
        myLogger.message(COMPILATION_STARTED);
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, text));
    }

    @Override
    public void processLine(@NotNull final String text) {
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, text));
    }

    @Override
    public void onLeave() {
        myLogger.message(COMPILATION_FINISHED);
    }
}
