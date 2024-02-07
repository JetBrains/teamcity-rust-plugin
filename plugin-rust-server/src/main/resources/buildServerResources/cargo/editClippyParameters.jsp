<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.clippyManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.clippyManifestKey}" className="longField"/>
        <span class="error" id="error_${params.clippyManifestKey}"></span>
        <span class="smallNote">Path to the manifesto.</span>
    </td>
</tr>