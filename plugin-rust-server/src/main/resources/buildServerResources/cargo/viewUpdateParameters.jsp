<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.updatePackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.updatePackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.updatePreciseKey]}">
    <div class="parameter">
        Precise: <props:displayValue name="${params.updatePreciseKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.updateAggressiveKey]}">
    <div class="parameter">
        Force updating all dependencies: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.updateManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.updateManifestKey}"/>
    </div>
</c:if>