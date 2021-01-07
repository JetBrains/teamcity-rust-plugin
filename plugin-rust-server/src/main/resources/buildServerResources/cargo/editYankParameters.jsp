<%@ page import="jetbrains.buildServer.rust.CargoParametersProvider" %>
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

<%
    CargoParametersProvider.replaceWithNewParameter(propertiesBean, params.getYankTokenKey(), params.getYankTokenKeySecure());
%>

<tr class="advancedSetting">
    <th><label for="${params.yankCrateKey}">Crate:</label></th>
    <td>
        <props:textProperty name="${params.yankCrateKey}" className="longField"/>
        <span class="error" id="error_${params.yankCrateKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.yankVersionKey}">Version:</label></th>
    <td>
        <props:textProperty name="${params.yankVersionKey}" className="longField"/>
        <span class="error" id="error_${params.yankVersionKey}"></span>
        <span class="smallNote">The version to yank or un-yank.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.yankUndoKey}"/>
        <label for="${params.yankUndoKey}">Undo a yank, putting a version back into the index</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.yankIndexKey}">Index:</label></th>
    <td>
        <props:textProperty name="${params.yankIndexKey}" className="longField"/>
        <span class="error" id="error_${params.yankIndexKey}"></span>
        <span class="smallNote">Registry index to yank from.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.yankTokenKeySecure}">Token:</label></th>
    <td>
        <props:passwordProperty name="${params.yankTokenKeySecure}" className="longField"/>
        <span class="error" id="error_${params.yankTokenKeySecure}"></span>
        <span class="smallNote">API token to use when authenticating.</span>
    </td>
</tr>