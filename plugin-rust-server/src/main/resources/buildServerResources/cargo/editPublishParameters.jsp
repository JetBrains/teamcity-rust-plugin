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
    <th><label for="${params.publishTokenKey}">Token:</label></th>
    <td>
        <props:textProperty name="${params.publishTokenKey}" className="longField"/>
        <span class="error" id="error_${params.publishTokenKey}"></span>
        <span class="smallNote">Token to use when uploading.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.publishHostKey}">Host:</label></th>
    <td>
        <props:textProperty name="${params.publishHostKey}" className="longField"/>
        <span class="error" id="error_${params.publishHostKey}"></span>
        <span class="smallNote">Host to upload the package to.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder">Package</th>
    <td>
        <props:checkboxProperty name="${params.publishNoVerifyKey}"/>
        <label for="${params.publishNoVerifyKey}">Do not verify package tarball before publish</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.publishManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.publishManifestKey}" className="longField"/>
        <span class="error" id="error_${params.publishManifestKey}"></span>
        <span class="smallNote">Path to the manifest of the package to publish.</span>
    </td>
</tr>