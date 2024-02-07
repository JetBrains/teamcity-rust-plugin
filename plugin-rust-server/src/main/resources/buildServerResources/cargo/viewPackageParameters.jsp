<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.packageNoVerifyKey]}">
    <div class="parameter">
        Do not verify the contents by building them: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.packageNoMetadataKey]}">
    <div class="parameter">
        Ignore warnings about a lack of human-usable metadata: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.packageManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.packageManifestKey}"/>
    </div>
</c:if>