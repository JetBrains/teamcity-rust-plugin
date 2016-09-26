/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Testing logger.
 */
public class CargoTestingLogger extends CargoDefaultLogger {
    private static Pattern TEST_PATTERN = Pattern.compile(
            "^test\\s([^\\s]+)\\s\\.\\.\\.\\s(ok|failed|ignored|bench)", Pattern.CASE_INSENSITIVE);
    private static final String TEST_SUITE_STARTED_FORMAT = "##teamcity[testSuiteStarted name='%s']";
    private static final String TEST_SUITE_FINISHED_FORMAT = "##teamcity[testSuiteFinished name='%s']";
    private static final String TEST_STARTED_FORMAT = "##teamcity[testStarted name='%s']";
    private static final String TEST_FINISHED_FORMAT = "##teamcity[testFinished name='%s' duration='%s']";
    private static final String TEST_IGNORED_FORMAT = "##teamcity[testIgnored name='%s' message='%s']";
    private static final String TEST_FAILED_FORMAT = "##teamcity[testFailed name='%s' message='%s' details='%s']";
    private static final String TEST_STDOUT_FORMAT = "##teamcity[testStdOut name='%s' out='%s']";
    private final BuildProgressLogger myLogger;
    private String myTestSuiteName;
    private String myTestName;
    private long testStartTime;

    public CargoTestingLogger(@NotNull final BuildProgressLogger logger) {
        super(logger);
        myLogger = logger;
    }

    @Override
    public void onEnter(@NotNull String testSuiteName) {
        myTestSuiteName = testSuiteName;
        testStartTime = System.currentTimeMillis();

        if (myTestSuiteName != null) myLogger.message(String.format(TEST_SUITE_STARTED_FORMAT, myTestSuiteName));
    }

    @Override
    public void processLine(@NotNull String text) {
        if (text.startsWith("test result")) return;

        final Matcher matcher = TEST_PATTERN.matcher(text);
        if (matcher.find()) {
            final String testName = matcher.group(1);
            final String result = matcher.group(2).toLowerCase();

            myLogger.message(String.format(TEST_STARTED_FORMAT, testName));
            myTestName = testName;

            if ("ok".equals(result)) {
                final long testDuration = System.currentTimeMillis() - testStartTime;
                myLogger.message(String.format(TEST_FINISHED_FORMAT, testName, testDuration));
            } else if ("ignored".equals(result)) {
                myLogger.message(String.format(TEST_IGNORED_FORMAT, testName, ""));
            } else if ("failed".equals(result)) {
                myLogger.message(String.format(TEST_FAILED_FORMAT, testName, "", ""));
            } else {
                myLogger.message(String.format(TEST_STDOUT_FORMAT, myTestName, text));
            }
        } else {
            myLogger.message(String.format(TEST_STDOUT_FORMAT, myTestName, text));
        }

        testStartTime = System.currentTimeMillis();
    }

    @Override
    public void onLeave() {
        if (myTestSuiteName != null) myLogger.message(String.format(TEST_SUITE_FINISHED_FORMAT, myTestSuiteName));
    }
}
