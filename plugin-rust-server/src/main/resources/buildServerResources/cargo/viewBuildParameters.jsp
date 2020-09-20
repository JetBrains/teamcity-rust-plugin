<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%--
  ~ Copyright 2000-2020 JetBrains s.r.o.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License").
  ~ See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.buildPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.buildPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.buildTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.buildTypeNameKey]}">
            <props:displayValue name="${params.buildTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.buildFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.buildTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.buildManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.buildParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.buildParallelKey}"/>
    </div>
</c:if>