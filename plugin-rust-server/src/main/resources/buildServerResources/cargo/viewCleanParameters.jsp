<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.cleanPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.cleanPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.cleanReleaseKey]}">
    <div class="parameter">
        Release mode: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.cleanTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.cleanTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.cleanManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.cleanManifestKey}"/>
    </div>
</c:if>