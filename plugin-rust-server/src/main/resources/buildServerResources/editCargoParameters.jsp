<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
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
        <props:selectProperty name="${params.verbosityKey}" className="mediumField">
            <props:option value="">&lt;Default&gt;</props:option>
            <props:option value="verbose">Verbose</props:option>
            <props:option value="quiet">Quiet</props:option>
        </props:selectProperty>
    </td>
</tr>
