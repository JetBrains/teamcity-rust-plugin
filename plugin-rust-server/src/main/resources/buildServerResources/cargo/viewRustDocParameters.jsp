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

<c:if test="${not empty propertiesBean.properties[params.rustDocOptionsKey]}">
    <div class="parameter">
        Options: <props:displayValue name="${params.rustDocOptionsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.rustDocPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.rustDocTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.rustDocTypeNameKey]}">
            <props:displayValue name="${params.rustDocTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.rustDocFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocOptionsKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.rustDocOptionsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.rustDocManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.rustDocParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.rustDocParallelKey}"/>
    </div>
</c:if>