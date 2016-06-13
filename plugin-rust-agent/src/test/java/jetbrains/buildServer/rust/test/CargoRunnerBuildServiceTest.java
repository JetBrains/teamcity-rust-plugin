/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.test;

import jetbrains.buildServer.rust.ArgumentsProvider;
import jetbrains.buildServer.rust.CargoConstants;
import jetbrains.buildServer.rust.cargo.*;
import jetbrains.buildServer.util.CollectionsUtil;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Tests for cargo runner.
 */
public class CargoRunnerBuildServiceTest {

    @Test(dataProvider = "testBuildArgumentsData")
    public void testBuildArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new BuildArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testCleanArgumentsData")
    public void testCleanArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new CleanArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testTestArgumentsData")
    public void testTestArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new TestArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testRunArgumentsData")
    public void testRunArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new RunArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @DataProvider(name = "testBuildArgumentsData")
    public Object[][] testBuildArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_PACKAGE, "name",
                        CargoConstants.PARAM_BUILD_RELEASE, "true"),
                        Arrays.asList("build", "--package", "name", "--release")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_TYPE, "--bin",
                        CargoConstants.PARAM_BUILD_TYPE_NAME, "name"),
                        Arrays.asList("build", "--bin", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_FEATURES, "name1 name2",
                        CargoConstants.PARAM_BUILD_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("build", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_TARGET, "name",
                        CargoConstants.PARAM_BUILD_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("build", "--target", "name", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testCleanArgumentsData")
    public Object[][] testCleanArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_CLEAN_PACKAGE, "name",
                        CargoConstants.PARAM_CLEAN_RELEASE, "true"),
                        Arrays.asList("clean", "--package", "name", "--release")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_CLEAN_TARGET, "name",
                        CargoConstants.PARAM_CLEAN_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("clean", "--target", "name", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testTestArgumentsData")
    public Object[][] testTestArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_PACKAGE, "name",
                        CargoConstants.PARAM_TEST_RELEASE, "true"),
                        Arrays.asList("test", "--package", "name", "--release")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_TYPE, "--bin",
                        CargoConstants.PARAM_TEST_TYPE_NAME, "name"),
                        Arrays.asList("test", "--bin", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_FEATURES, "name1 name2",
                        CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("test", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_TARGET, "name",
                        CargoConstants.PARAM_TEST_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("test", "--target", "name", "--manifest-path", "/path/to/manifest")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_ARGUMENTS, "name",
                        CargoConstants.PARAM_TEST_NO_RUN_TESTS, "true",
                        CargoConstants.PARAM_TEST_NO_FAIL_FAST, "true"),
                        Arrays.asList("test", "--no-run", "--no-fail-fast", "name")},
        };
    }

    @DataProvider(name = "testRunArgumentsData")
    public Object[][] testRunArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_ARGUMENTS, "name",
                        CargoConstants.PARAM_RUN_RELEASE, "true"),
                        Arrays.asList("run", "--release", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_TYPE, "--bin",
                        CargoConstants.PARAM_RUN_TYPE_NAME, "name"),
                        Arrays.asList("run", "--bin", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_FEATURES, "name1 name2",
                        CargoConstants.PARAM_RUN_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("run", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_TARGET, "name",
                        CargoConstants.PARAM_RUN_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("run", "--target", "name", "--manifest-path", "/path/to/manifest")},
        };
    }
}
