<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.docPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.docPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docReleaseKey]}">
    <div class="parameter">
        Release mode: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docNoDependenciesKey]}">
    <div class="parameter">
        Do not build documentation for dependencies: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.docFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.docTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.docManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.docParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.docParallelKey}"/>
    </div>
</c:if>