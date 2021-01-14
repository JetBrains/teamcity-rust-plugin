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
    <th><label for="${params.updatePackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.updatePackageKey}" className="longField"/>
        <span class="error" id="error_${params.updatePackageKey}"></span>
        <span class="smallNote">Package to update.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.updatePreciseKey}">Precise:</label></th>
    <td>
        <props:textProperty name="${params.updatePreciseKey}" className="longField"/>
        <span class="error" id="error_${params.updatePreciseKey}"></span>
        <span class="smallNote">Update a single dependency to exactly precise.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.updateAggressiveKey}"/>
        <label for="${params.updateAggressiveKey}">Force updating all dependencies</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.updateManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.updateManifestKey}" className="longField"/>
        <span class="error" id="error_${params.updateManifestKey}"></span>
        <span class="smallNote">Path to the crate's manifest.</span>
    </td>
</tr>