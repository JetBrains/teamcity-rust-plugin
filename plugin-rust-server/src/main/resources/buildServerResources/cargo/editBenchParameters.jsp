<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%--
  ~ Copyright 2000-2020 JetBrains s.r.o.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License").
  ~ See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr>
    <th><label for="${params.benchArgumentsKey}">Arguments:</label></th>
    <td>
        <props:textProperty name="${params.benchArgumentsKey}" className="longField"/>
        <span class="error" id="error_${params.benchArgumentsKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.benchPackageKey}" className="longField"/>
        <span class="error" id="error_${params.benchPackageKey}"></span>
        <span class="smallNote">Package to run benchmarks for.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchTypeKey}">Benchmark:</label></th>
    <td>
        <props:selectProperty name="${params.benchTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.benchTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.benchTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.benchReleaseKey}"/>
        <label for="${params.benchReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.benchNoRunKey}"/>
        <label for="${params.benchNoRunKey}">Compile, but do not run benchmarks</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.benchFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.benchFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.benchNoDefaultFeaturesKey}"/>
        <label for="${params.benchNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.benchTargetKey}" className="longField"/>
        <span class="error" id="error_${params.benchTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.benchManifestKey}" className="longField"/>
        <span class="error" id="error_${params.benchManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.benchParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.benchParallelKey}" className="longField"/>
        <span class="error" id="error_${params.benchParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>