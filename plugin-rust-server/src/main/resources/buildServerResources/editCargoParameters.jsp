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
<jsp:useBean id="teamcityPluginResourcesPath" scope="request" type="java.lang.String"/>

<link rel="stylesheet" href="${teamcityPluginResourcesPath}rust-settings.css">

<props:selectSectionProperty name="${params.commandKey}" title="Command:" note="">
    <c:forEach items="${params.types}" var="type">
        <props:selectSectionPropertyContent value="${type.name}" caption="${type.name}">
            <jsp:include page="${teamcityPluginResourcesPath}/cargo/${type.editPage}"/>
        </props:selectSectionPropertyContent>
    </c:forEach>
</props:selectSectionProperty>

<tr class="advancedSetting">
    <th><label for="${params.verbosityKey}">Output verbosity:</label></th>
    <td>
        <props:selectProperty name="${params.verbosityKey}" className="smallField">
            <props:option value="">&lt;Default&gt;</props:option>
            <props:option value="--verbose">Verbose</props:option>
            <props:option value="--quiet">Quiet</props:option>
        </props:selectProperty>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.configKey}">Configuration options:</label></th>
    <td>
        <props:textProperty name="${params.configKey}" className="longField"/>
        <span class="smallNote">
            Space-separated list of Cargo configuration options in KEY=VALUE format.
        </span>
    </td>
</tr>

<tr class="advancedSetting" id="${params.toolchainKey}-wrapper">
    <th><label for="${params.toolchainKey}">Toolchain version:</label></th>
    <td>
        <props:textProperty
                name="${params.toolchainKey}"
                className="longField"
        />
        <span class="error" id="error_${params.toolchainKey}"></span>
        <span class="smallNote">
            Defines rust toolchain version: stable, beta, nightly or custom.<br/>
            Leave blank to use default version installed on the build agent.
        </span>
        <span class="error" id="error_${params.toolchainKey}"></span>
    </td>
</tr>


<tr class="advancedSetting">
    <th><label for="${params.additionalArguments}">Additional arguments:</label></th>
    <td>
        <props:textProperty
                name="${params.additionalArguments}"
                className="longField"
        />
        <span class="error" id="error_${params.additionalArguments}"></span>
    </td>
</tr>

<bs:executeOnce id="rustToolChainVersionUpdate">
    <script>
        $j(document).ready(function() {
            var dockerImageId = "#plugin\\.docker\\.imageId";

            BS.CargoParametersPage = {
                updateToolchainVersionField: function() {
                    var value = $j(dockerImageId).val()
                    var toolChainKeyWrapper = $j("#${params.toolchainKey}-wrapper");
                    var toolchainKey = $j("#${params.toolchainKey}");

                    if (value) {
                        toolChainKeyWrapper.hide();
                        toolchainKey.val("");
                    } else {
                        toolChainKeyWrapper.show();
                    }
                }
            };

            BS.CargoParametersPage.updateToolchainVersionField();
            $j(dockerImageId).change(BS.CargoParametersPage.updateToolchainVersionField);
            $j(dockerImageId).keyup(BS.CargoParametersPage.updateToolchainVersionField);
        });
    </script>
</bs:executeOnce>
