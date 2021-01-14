<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%--
  ~ Copyright 2000-2021 JetBrains s.r.o.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License").
  ~ See LICENSE in the project root for license information.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.rustcOptsKey]}">
    <div class="parameter">
        Options: <props:displayValue name="${params.rustcOptsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.rustcPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.rustcTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.rustcTypeNameKey]}">
            <props:displayValue name="${params.rustcTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.rustcFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.rustcTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.rustcManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustcParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.rustcParallelKey}"/>
    </div>
</c:if>