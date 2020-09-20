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

<c:if test="${not empty propertiesBean.properties[params.runArgumentsKey]}">
    <div class="parameter">
        Arguments: <props:displayValue name="${params.runArgumentsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.runTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.runTypeNameKey]}">
            <props:displayValue name="${params.runTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.runFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.runTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.runManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.runParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.runParallelKey}"/>
    </div>
</c:if>