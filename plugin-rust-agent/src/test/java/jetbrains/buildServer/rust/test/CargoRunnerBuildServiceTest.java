/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.test;

import jetbrains.buildServer.rust.ArgumentsProvider;
import jetbrains.buildServer.rust.CargoConstants;
import jetbrains.buildServer.rust.cargo.BuildArgumentsProvider;
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
}
