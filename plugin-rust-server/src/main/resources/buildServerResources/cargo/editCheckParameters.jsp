<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%--
  - Copyright 2000-2023 JetBrains s.r.o.
  -
  - Licensed under the Apache License, Version 2.0 (the "License").
  - See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.checkPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.checkPackageKey}" className="longField"/>
        <span class="error" id="error_${params.checkPackageKey}"></span>
        <span class="smallNote">Package to check for errors.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.checkTypeKey}">Check:</label></th>
    <td>
        <props:selectProperty name="${params.checkTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.checkTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.checkTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.checkFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.checkFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.checkFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.checkNoDefaultFeaturesKey}"/>
        <label for="${params.checkNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.checkReleaseKey}"/>
        <label for="${params.checkReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.checkTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.checkTargetKey}" className="longField"/>
        <span class="error" id="error_${params.checkTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.checkManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.checkManifestKey}" className="longField"/>
        <span class="error" id="error_${params.checkManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.checkParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.checkParallelKey}" className="longField"/>
        <span class="error" id="error_${params.checkParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>
