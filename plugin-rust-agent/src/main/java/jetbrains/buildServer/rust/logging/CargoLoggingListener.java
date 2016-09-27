/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import jetbrains.buildServer.agent.runner.ProcessListenerAdapter;
import jetbrains.buildServer.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles build messages from cargo tool.
 */
public class CargoLoggingListener extends ProcessListenerAdapter {
    private static Pattern myStatement = Pattern.compile("^([\\w][\\w-]+\\:?)\\s+(.*)?$");
    private static Pattern myTestsStart = Pattern.compile("^running \\d+ tests?$");
    private final CargoLoggerFactory myLoggerFactory;
    private CargoLogger myLogger;
    private String myLastLine;

    public CargoLoggingListener(@NotNull final CargoLoggerFactory loggerFactory) {
        myLoggerFactory = loggerFactory;
        myLogger = loggerFactory.getLogger(CargoState.Default);
    }

    @Override
    public void onStandardOutput(@NotNull String text) {
        text = text.trim();

        if (StringUtil.isEmptyOrSpaces(text)) return;

        final String lastLine = myLastLine;
        myLastLine = text.trim();

        final Matcher testsMatcher = myTestsStart.matcher(text);
        if (testsMatcher.find()) {
            final String testSuiteName = getTestSuiteName(lastLine);
            changeState(CargoState.Testing, testSuiteName);
            return;
        }

        final Matcher stateMatcher = myStatement.matcher(text);
        if (stateMatcher.find()) {
            final String stateKey = stateMatcher.group(1);
            final String stateText = stateMatcher.group(2);

            final CargoState state = CargoState.get(stateKey);
            if (state != null && myLogger.canChangeState(state, stateText)) {
                changeState(state, stateText);
                return;
            }
        }

        myLogger.processLine(text);
    }

    private void changeState(@NotNull final CargoState state, @NotNull final String text) {
        final CargoLogger logger = myLoggerFactory.getLogger(state);

        myLogger.onLeave();
        logger.onEnter(text);
        myLogger = logger;
    }

    @Override
    public void onErrorOutput(@NotNull final String text) {
        myLogger.processError(text);
    }

    @Override
    public void processFinished(int exitCode) {
        myLogger.onLeave();
    }

    @NotNull
    private static String getTestSuiteName(@NotNull final String text) {
        if (text.startsWith(CargoState.Running.toString())) {
            int slashIndex = text.lastIndexOf("/");
            if (slashIndex < 0) {
                slashIndex = text.lastIndexOf("\\");
            }

            if (slashIndex > 0) {
                final int dashIndex = text.lastIndexOf("-");
                if (dashIndex > slashIndex) {
                    return text.substring(slashIndex + 1, dashIndex);
                }
            }
        } else if (text.startsWith(CargoState.DocTests.toString())) {
            return text;
        }

        return "";
    }
}
