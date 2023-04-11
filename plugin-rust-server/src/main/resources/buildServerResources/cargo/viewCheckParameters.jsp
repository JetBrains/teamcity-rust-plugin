<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%--
  - Copyright 2000-2023 JetBrains s.r.o.
  -
  - Licensed under the Apache License, Version 2.0 (the "License").
  - See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.checkPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.checkPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.checkTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.checkTypeNameKey]}">
            <props:displayValue name="${params.checkTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.checkFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.checkTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.checkManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.checkParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.checkParallelKey}"/>
    </div>
</c:if>
