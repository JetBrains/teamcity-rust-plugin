/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.test;

import jetbrains.buildServer.agent.BuildProgressLogger;
import jetbrains.buildServer.rust.logging.CargoLoggerFactory;
import jetbrains.buildServer.rust.logging.CargoLoggingListener;
import jetbrains.buildServer.util.FileUtil;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jetbrains.annotations.NotNull;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitry.Tretyakov
 *         Date: 21.06.2016
 *         Time: 0:05
 */
public class CargoLoggingListenerTest {

    @Test(dataProvider = "getTestTransformations")
    public void testTransformation(final String filename) throws IOException {
        final File originalFile = new File("src/test/resources/cargo/original/" + filename);
        final List<String> original = new ArrayList<String>(FileUtil.readFile(originalFile));
        final File processedFile = new File("src/test/resources/cargo/processed/" + filename);
        final List<String> processed = new ArrayList<String>(FileUtil.readFile(processedFile));
        final Mockery m = new Mockery();
        final BuildProgressLogger logger = m.mock(BuildProgressLogger.class);
        final Matcher<String> matcher = getMatcher(processed);
        final int numberOfLines = processed.size();
        m.checking(new Expectations() {{
            exactly(numberOfLines).of(logger).message(with(matcher));
        }});

        final CargoLoggerFactory loggerFactory = new CargoLoggerFactory(logger);
        final CargoLoggingListener listener = new CargoLoggingListener(loggerFactory);

        for (String message : original) {
            listener.onStandardOutput(message);
        }

        listener.processFinished(0);

        m.assertIsSatisfied();
    }

    private BaseMatcher<String> getMatcher(@NotNull final List<String> processed) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object actual) {
                return processed.size() > 0 && isEquals((String) actual, processed.remove(0));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("List matcher");
            }
        };
    }

    private boolean isEquals(String actual, String expected) {
        actual = actual.replaceAll("\\s(duration='\\d+')", "");
        return actual.equals(expected);
    }

    @DataProvider
    public static Object[][] getTestTransformations() {
        return new Object[][]{
                {"cargoTests1.txt"}
        };
    }
}
