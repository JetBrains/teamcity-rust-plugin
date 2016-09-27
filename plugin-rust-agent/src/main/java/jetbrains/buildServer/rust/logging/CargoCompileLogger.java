/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Compiling logger.
 */
public class CargoCompileLogger extends CargoDefaultLogger {
    private static Pattern PROJECT_NAME_PATTERN = Pattern.compile("([^\\s]+)");
    private static final String COMPILATION_STARTED_FORMAT = "##teamcity[compilationStarted compiler='rustc%s']";
    private static final String COMPILATION_FINISHED_FORMAT = "##teamcity[compilationFinished compiler='rustc%s']";
    private static final String COMPILATION_MESSAGE_FORMAT = "##teamcity[message text='%s']";
    private final BuildProgressLogger myLogger;
    private String myProjectName;

    public CargoCompileLogger(@NotNull final BuildProgressLogger logger) {
        super(logger);
        myLogger = logger;
    }

    @Override
    public void onEnter(@NotNull final String text) {
        final Matcher matcher = PROJECT_NAME_PATTERN.matcher(text);
        myProjectName  = matcher.find() ? ":" + matcher.group(1) : "";
        myLogger.message(String.format(COMPILATION_STARTED_FORMAT, myProjectName));

        final String message = String.format("%s %s", CargoState.Compiling, text);
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, message));
    }

    @Override
    public void processLine(@NotNull final String text) {
        myLogger.message(String.format(COMPILATION_MESSAGE_FORMAT, text));
    }

    @Override
    public boolean canChangeState(CargoState state, String text) {
        return !(state == CargoState.Running && text.startsWith("`rustc"));
    }

    @Override
    public void onLeave() {
        myLogger.message(String.format(COMPILATION_FINISHED_FORMAT, myProjectName));
    }
}
