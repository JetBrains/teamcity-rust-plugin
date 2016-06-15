<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.testArgumentsKey]}">
    <div class="parameter">
        Arguments: <props:displayValue name="${params.testArgumentsKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testPackageKey]}">
    <div class="parameter">
        Package: <props:displayValue name="${params.testPackageKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testTypeKey]}">
    <div class="parameter">
        Type: <props:displayValue name="${params.testTypeKey}"/>
        <c:if test="${not empty propertiesBean.properties[params.testTypeNameKey]}">
            <props:displayValue name="${params.testTypeNameKey}"/>
        </c:if>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testReleaseKey]}">
    <div class="parameter">
        Build artifacts in release mode, with optimizations: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testNoRunKey]}">
    <div class="parameter">
        Compile, but do not run tests: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testNoFailFastKey]}">
    <div class="parameter">
        Run all tests regardless of failure: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testFeaturesKey]}">
    <div class="parameter">
        Features: <props:displayValue name="${params.testFeaturesKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testNoDefaultFeaturesKey]}">
    <div class="parameter">
        Do not build default features: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testTargetKey]}">
    <div class="parameter">
        Target: <props:displayValue name="${params.testTargetKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testManifestKey]}">
    <div class="parameter">
        Manifest: <props:displayValue name="${params.testManifestKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.testParallelKey]}">
    <div class="parameter">
        Parallel jobs: <props:displayValue name="${params.testParallelKey}"/>
    </div>
</c:if>