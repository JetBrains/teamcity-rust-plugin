/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.test

import jetbrains.buildServer.rust.ArgumentsProvider
import jetbrains.buildServer.rust.CargoConstants
import jetbrains.buildServer.rust.cargo.*
import jetbrains.buildServer.util.CollectionsUtil
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import java.util.Arrays

/**
 * Tests for cargo runner.
 */
class CargoRunnerBuildServiceTest {

    @Test(dataProvider = "testBuildArgumentsData")
    fun testBuildArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = BuildArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testCleanArgumentsData")
    fun testCleanArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = CleanArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testTestArgumentsData")
    fun testTestArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = TestArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testRunArgumentsData")
    fun testRunArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = RunArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testBenchArgumentsData")
    fun testBenchArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = BenchArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testDocArgumentsData")
    fun testDocArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = DocArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testPackageArgumentsData")
    fun testPackageArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = PackageArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testPublishArgumentsData")
    fun testPublishArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = PublishArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testRustcArgumentsData")
    fun testRustcArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = RustcArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testLoginArgumentsData")
    fun testLoginArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = LoginArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testUpdateArgumentsData")
    fun testUpdateArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = UpdateArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testRustDocArgumentsData")
    fun testRustDocArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = RustDocArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testYankArgumentsData")
    fun testYankArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = YankArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @DataProvider(name = "testBuildArgumentsData")
    fun testBuildArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_BUILD_PACKAGE, "name",
                CargoConstants.PARAM_BUILD_RELEASE, "true"), Arrays.asList("build", "--package", "name", "--release")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_TYPE, "--bin",
                        CargoConstants.PARAM_BUILD_TYPE_NAME, "name"), Arrays.asList("build", "--bin", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_FEATURES, "name1 name2",
                        CargoConstants.PARAM_BUILD_NO_DEFAULT_FEATURES, "true"), Arrays.asList("build", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BUILD_TARGET, "name",
                        CargoConstants.PARAM_BUILD_MANIFEST, "/path/to/manifest"), Arrays.asList("build", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testCleanArgumentsData")
    fun testCleanArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_CLEAN_PACKAGE, "name",
                CargoConstants.PARAM_CLEAN_RELEASE, "true"), Arrays.asList("clean", "--package", "name", "--release")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_CLEAN_TARGET, "name",
                        CargoConstants.PARAM_CLEAN_MANIFEST, "/path/to/manifest"), Arrays.asList("clean", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testTestArgumentsData")
    fun testTestArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_TEST_PACKAGE, "name",
                CargoConstants.PARAM_TEST_RELEASE, "true"), Arrays.asList("test", "--package", "name", "--release")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_TYPE, "--bin",
                        CargoConstants.PARAM_TEST_TYPE_NAME, "name"), Arrays.asList("test", "--bin", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_FEATURES, "name1 name2",
                        CargoConstants.PARAM_TEST_NO_DEFAULT_FEATURES, "true"), Arrays.asList("test", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_TARGET, "name",
                        CargoConstants.PARAM_TEST_MANIFEST, "/path/to/manifest"), Arrays.asList("test", "--target", "name", "--manifest-path", "/path/to/manifest")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_TEST_ARGUMENTS, "name",
                        CargoConstants.PARAM_TEST_NO_RUN, "true",
                        CargoConstants.PARAM_TEST_NO_FAIL_FAST, "true"), Arrays.asList("test", "--no-run", "--no-fail-fast", "name")))
    }

    @DataProvider(name = "testRunArgumentsData")
    fun testRunArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_RUN_ARGUMENTS, "name",
                CargoConstants.PARAM_RUN_RELEASE, "true"), Arrays.asList("run", "--release", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_TYPE, "--bin",
                        CargoConstants.PARAM_RUN_TYPE_NAME, "name"), Arrays.asList("run", "--bin", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_FEATURES, "name1 name2",
                        CargoConstants.PARAM_RUN_NO_DEFAULT_FEATURES, "true"), Arrays.asList("run", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUN_TARGET, "name",
                        CargoConstants.PARAM_RUN_MANIFEST, "/path/to/manifest"), Arrays.asList("run", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testBenchArgumentsData")
    fun testBenchArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_BENCH_PACKAGE, "name",
                CargoConstants.PARAM_BENCH_RELEASE, "true"), Arrays.asList("bench", "--package", "name", "--release")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_TYPE, "--bin",
                        CargoConstants.PARAM_BENCH_TYPE_NAME, "name"), Arrays.asList("bench", "--bin", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_FEATURES, "name1 name2",
                        CargoConstants.PARAM_BENCH_NO_DEFAULT_FEATURES, "true"), Arrays.asList("bench", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_TARGET, "name",
                        CargoConstants.PARAM_BENCH_MANIFEST, "/path/to/manifest"), Arrays.asList("bench", "--target", "name", "--manifest-path", "/path/to/manifest")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_BENCH_ARGUMENTS, "name",
                        CargoConstants.PARAM_BENCH_NO_RUN, "true"), Arrays.asList("bench", "--no-run", "name")))
    }

    @DataProvider(name = "testDocArgumentsData")
    fun testDocArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_DOC_PACKAGE, "name",
                CargoConstants.PARAM_DOC_RELEASE, "true",
                CargoConstants.PARAM_DOC_NO_DEPS, "true"), Arrays.asList("doc", "--package", "name", "--release", "--no-deps")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_DOC_FEATURES, "name1 name2",
                        CargoConstants.PARAM_DOC_NO_DEFAULT_FEATURES, "true"), Arrays.asList("doc", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_DOC_TARGET, "name",
                        CargoConstants.PARAM_DOC_MANIFEST, "/path/to/manifest"), Arrays.asList("doc", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testPackageArgumentsData")
    fun testPackageArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_PACKAGE_NO_METADATA, "true",
                CargoConstants.PARAM_PACKAGE_NO_VERIFY, "true",
                CargoConstants.PARAM_PACKAGE_MANIFEST, "/path/to/manifest"), Arrays.asList("package", "--no-verify", "--no-metadata", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testPublishArgumentsData")
    fun testPublishArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_PUBLISH_HOST, "host",
                CargoConstants.PARAM_PUBLISH_TOKEN, "token"), Arrays.asList("publish", "--host", "host", "--token", "token")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_PUBLISH_NO_VERIFY, "true",
                        CargoConstants.PARAM_PUBLISH_MANIFEST, "/path/to/manifest"), Arrays.asList("publish", "--no-verify", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testRustcArgumentsData")
    fun testRustcArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_RUSTC_PACKAGE, "name",
                CargoConstants.PARAM_RUSTC_RELEASE, "true",
                CargoConstants.PARAM_RUSTC_OPTS, "opt1 opt2"), Arrays.asList("rustc", "--package", "name", "--release", "opt1", "opt2")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_TYPE, "--bin",
                        CargoConstants.PARAM_RUSTC_TYPE_NAME, "name"), Arrays.asList("rustc", "--bin", "name")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_FEATURES, "name1 name2",
                        CargoConstants.PARAM_RUSTC_NO_DEFAULT_FEATURES, "true"), Arrays.asList("rustc", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTC_TARGET, "name",
                        CargoConstants.PARAM_RUSTC_MANIFEST, "/path/to/manifest"), Arrays.asList("rustc", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testLoginArgumentsData")
    fun testLoginArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_LOGIN_HOST, "host",
                CargoConstants.PARAM_LOGIN_TOKEN, "token"), Arrays.asList("login", "--host", "host", "token")))
    }

    @DataProvider(name = "testUpdateArgumentsData")
    fun testUpdateArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_UPDATE_PACKAGE, "name",
                CargoConstants.PARAM_UPDATE_PRECISE, "precise"), Arrays.asList("update", "--package", "name", "--precise", "precise")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_UPDATE_AGGRESSIVE, "true",
                        CargoConstants.PARAM_UPDATE_MANIFEST, "/path/to/manifest"), Arrays.asList("update", "--aggressive", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testRustDocArgumentsData")
    fun testRustDocArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_RUSTDOC_OPTS, "opt1 opt2",
                CargoConstants.PARAM_RUSTDOC_PACKAGE, "name",
                CargoConstants.PARAM_RUSTDOC_RELEASE, "true"), Arrays.asList("rustdoc", "--package", "name", "--release", "opt1", "opt2")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTDOC_FEATURES, "name1 name2",
                        CargoConstants.PARAM_RUSTDOC_NO_DEFAULT_FEATURES, "true"), Arrays.asList("rustdoc", "--features", "name1 name2", "--no-default-features")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_RUSTDOC_TARGET, "name",
                        CargoConstants.PARAM_RUSTDOC_MANIFEST, "/path/to/manifest"), Arrays.asList("rustdoc", "--target", "name", "--manifest-path", "/path/to/manifest")))
    }

    @DataProvider(name = "testYankArgumentsData")
    fun testYankArgumentsData(): Array<Array<Any>> {
        return arrayOf(arrayOf(CollectionsUtil.asMap(
                CargoConstants.PARAM_YANK_VERSION, "version",
                CargoConstants.PARAM_YANK_UNDO, "true"), Arrays.asList("yank", "--vers", "version", "--undo")),

                arrayOf(CollectionsUtil.asMap(
                        CargoConstants.PARAM_YANK_INDEX, "index",
                        CargoConstants.PARAM_YANK_TOKEN, "token",
                        CargoConstants.PARAM_YANK_CRATE, "crate"), Arrays.asList("yank", "--index", "index", "--token", "token", "crate")))
    }
}
