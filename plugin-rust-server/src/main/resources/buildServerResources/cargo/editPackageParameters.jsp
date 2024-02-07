<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th class="noBorder">Package</th>
    <td>
        <props:checkboxProperty name="${params.packageNoVerifyKey}"/>
        <label for="${params.packageNoVerifyKey}">Do not verify the contents by building them</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.packageNoMetadataKey}"/>
        <label for="${params.packageNoMetadataKey}">Ignore warnings about a lack of human-usable metadata</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.packageManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.packageManifestKey}" className="longField"/>
        <span class="error" id="error_${params.packageManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>