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
    <th><label for="${params.cleanPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.cleanPackageKey}" className="longField"/>
        <span class="error" id="error_${params.cleanPackageKey}"></span>
        <span class="smallNote">Package to clean artifacts for.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.cleanReleaseKey}"/>
        <label for="${params.cleanReleaseKey}">Whether or not to clean release artifacts</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.cleanTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.cleanTargetKey}" className="longField"/>
        <span class="error" id="error_${params.cleanTargetKey}"></span>
        <span class="smallNote">Target triple to clean output for.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.cleanManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.cleanManifestKey}" className="longField"/>
        <span class="error" id="error_${params.cleanManifestKey}"></span>
        <span class="smallNote">Path to the manifest to to the package to clean.</span>
    </td>
</tr>