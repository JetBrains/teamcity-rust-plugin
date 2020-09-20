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

<c:if test="${not empty propertiesBean.properties[params.benchArgumentsKey]}">
    <div class="parameter">
        Arguments: <props:displayValue name="${params.benchArgumentsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.benchPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.benchTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.benchTypeNameKey]}">
            <props:displayValue name="${params.benchTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchNoRunKey]}">
    <div class="parameter">
        Compile, but do not run benchmarks: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.benchFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.benchTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.benchManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.benchParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.benchParallelKey}"/>
    </div>
</c:if>