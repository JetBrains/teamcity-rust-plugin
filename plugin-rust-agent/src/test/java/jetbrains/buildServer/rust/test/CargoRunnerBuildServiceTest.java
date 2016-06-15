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

    @Test(dataProvider = "testBenchArgumentsData")
    public void testBenchArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new BenchArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testDocArgumentsData")
    public void testDocArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new DocArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testPackageArgumentsData")
    public void testPackageArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new PackageArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testPublishArgumentsData")
    public void testPublishArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new PublishArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testRustcArgumentsData")
    public void testRustcArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new RustcArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testLoginArgumentsData")
    public void testLoginArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new LoginArgumentsProvider();
        final List<String> result = argumentsProvider.getArguments(parameters);

        Assert.assertEquals(result, arguments);
    }

    @Test(dataProvider = "testUpdateArgumentsData")
    public void testUpdateArguments(final Map<String, String> parameters, final List<String> arguments) {
        final ArgumentsProvider argumentsProvider = new UpdateArgumentsProvider();
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
                        CargoConstants.PARAM_TEST_NO_RUN, "true",
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

    @DataProvider(name = "testBenchArgumentsData")
    public Object[][] testBenchArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_PACKAGE, "name",
                        CargoConstants.PARAM_BENCH_RELEASE, "true"),
                        Arrays.asList("bench", "--package", "name", "--release")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_TYPE, "--bin",
                        CargoConstants.PARAM_BENCH_TYPE_NAME, "name"),
                        Arrays.asList("bench", "--bin", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_FEATURES, "name1 name2",
                        CargoConstants.PARAM_BENCH_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("bench", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_TARGET, "name",
                        CargoConstants.PARAM_BENCH_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("bench", "--target", "name", "--manifest-path", "/path/to/manifest")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_ARGUMENTS, "name",
                        CargoConstants.PARAM_BENCH_NO_RUN, "true"),
                        Arrays.asList("bench", "--no-run", "name")},
        };
    }

    @DataProvider(name = "testDocArgumentsData")
    public Object[][] testDocArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_DOC_PACKAGE, "name",
                        CargoConstants.PARAM_DOC_RELEASE, "true",
                        CargoConstants.PARAM_DOC_NO_DEPS, "true"),
                        Arrays.asList("doc", "--package", "name", "--release", "--no-deps")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_DOC_FEATURES, "name1 name2",
                        CargoConstants.PARAM_DOC_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("doc", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_DOC_TARGET, "name",
                        CargoConstants.PARAM_DOC_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("doc", "--target", "name", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testPackageArgumentsData")
    public Object[][] testPackageArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_PACKAGE_NO_METADATA, "true",
                        CargoConstants.PARAM_PACKAGE_NO_VERIFY, "true",
                        CargoConstants.PARAM_PACKAGE_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("package", "--no-verify", "--no-metadata", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testPublishArgumentsData")
    public Object[][] testPublishArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_PUBLISH_HOST, "host",
                        CargoConstants.PARAM_PUBLISH_TOKEN, "token"),
                        Arrays.asList("publish", "--host", "host", "--token", "token")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_PUBLISH_NO_VERIFY, "true",
                        CargoConstants.PARAM_PUBLISH_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("publish", "--no-verify", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testRustcArgumentsData")
    public Object[][] testRustcArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_PACKAGE, "name",
                        CargoConstants.PARAM_RUSTC_RELEASE, "true",
                        CargoConstants.PARAM_RUSTC_OPTS, "opt1 opt2"),
                        Arrays.asList("rustc", "--package", "name", "--release", "opt1", "opt2")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_TYPE, "--bin",
                        CargoConstants.PARAM_RUSTC_TYPE_NAME, "name"),
                        Arrays.asList("rustc", "--bin", "name")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_FEATURES, "name1 name2",
                        CargoConstants.PARAM_RUSTC_NO_DEFAULT_FEATURES, "true"),
                        Arrays.asList("rustc", "--features", "name1 name2", "--no-default-features")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_TARGET, "name",
                        CargoConstants.PARAM_RUSTC_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("rustc", "--target", "name", "--manifest-path", "/path/to/manifest")},
        };
    }

    @DataProvider(name = "testLoginArgumentsData")
    public Object[][] testLoginArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_LOGIN_HOST, "host",
                        CargoConstants.PARAM_LOGIN_TOKEN, "token"),
                        Arrays.asList("login", "--host", "host", "token")},
        };
    }

    @DataProvider(name = "testUpdateArgumentsData")
    public Object[][] testUpdateArgumentsData() {
        return new Object[][]{
                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_UPDATE_PACKAGE, "name",
                        CargoConstants.PARAM_UPDATE_PRECISE, "precise"),
                        Arrays.asList("update", "--package", "name", "--precise", "precise")},

                {CollectionsUtil.asMap(
                        CargoConstants.PARAM_UPDATE_AGGRESSIVE, "true",
                        CargoConstants.PARAM_UPDATE_MANIFEST, "/path/to/manifest"),
                        Arrays.asList("update", "--aggressive", "--manifest-path", "/path/to/manifest")},
        };
    }
}
