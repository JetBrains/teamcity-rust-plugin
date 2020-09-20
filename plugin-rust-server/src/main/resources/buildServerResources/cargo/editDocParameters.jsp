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

<tr class="advancedSetting">
    <th><label for="${params.docPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.docPackageKey}" className="longField"/>
        <span class="error" id="error_${params.docPackageKey}"></span>
        <span class="smallNote">Package to document.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.docReleaseKey}"/>
        <label for="${params.docReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.docNoDependenciesKey}"/>
        <label for="${params.docNoDependenciesKey}">Do not build documentation for dependencies</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.docFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.docFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.docFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.docNoDefaultFeaturesKey}"/>
        <label for="${params.docNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.docTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.docTargetKey}" className="longField"/>
        <span class="error" id="error_${params.docTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.docManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.docManifestKey}" className="longField"/>
        <span class="error" id="error_${params.docManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.docParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.docParallelKey}" className="longField"/>
        <span class="error" id="error_${params.docParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>