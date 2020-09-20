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

<c:if test="${not empty propertiesBean.properties[params.publishTokenKey]}">
    <div class="parameter">
        Token: <props:displayValue name="${params.publishTokenKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.publishHostKey]}">
    <div class="parameter">
        Host: <props:displayValue name="${params.publishHostKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.publishNoVerifyKey]}">
    <div class="parameter">
        Do not verify package tarball before publish: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.publishManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.publishManifestKey}"/>
    </div>
</c:if>