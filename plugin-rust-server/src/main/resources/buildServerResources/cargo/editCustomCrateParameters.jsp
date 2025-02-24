<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.customCrateCommandName}">Crate name:</label></th>
    <td>
        <props:textProperty name="${params.customCrateCommandName}" className="longField"/>
        <span class="error" id="error_${params.customCrateCommandName}"></span>
        <span class="smallNote">Crate name without `cargo-` prefix</span>
    </td>
</tr>
<tr class="advancedSetting">
    <th><label for="${params.customCrateCommandVersion}">Crate version:</label></th>
    <td>
        <props:textProperty name="${params.customCrateCommandVersion}" className="longField"/>
        <span class="error" id="error_${params.customCrateCommandVersion}"></span>
        <span class="smallNote">Crate version (leave blank to use the latest)</span>
    </td>
</tr>
