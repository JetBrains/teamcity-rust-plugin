<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%--
  ~ Copyright 2000-2021 JetBrains s.r.o.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License").
  ~ See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.rustDocOptionsKey}">Options:</label></th>
    <td>
        <props:textProperty name="${params.rustDocOptionsKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocOptionsKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustDocPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.rustDocPackageKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocPackageKey}"></span>
        <span class="smallNote">Package to document.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustDocTypeKey}">Build:</label></th>
    <td>
        <props:selectProperty name="${params.rustDocTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.rustDocTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.rustDocTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.rustDocReleaseKey}"/>
        <label for="${params.rustDocReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.docFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.rustDocFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.rustDocNoDefaultFeaturesKey}"/>
        <label for="${params.rustDocNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustDocTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.rustDocTargetKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustDocManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.rustDocManifestKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustDocParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.rustDocParallelKey}" className="longField"/>
        <span class="error" id="error_${params.rustDocParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>