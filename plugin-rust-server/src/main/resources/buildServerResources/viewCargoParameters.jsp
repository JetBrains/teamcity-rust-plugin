<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>
<jsp:useBean id="teamcityPluginResourcesPath" scope="request" type="java.lang.String"/>

<c:forEach items="${params.types}" var="type">
    <c:if test="${propertiesBean.properties[params.commandKey] eq type.name}">
        <div class="parameter">
            Command: <strong><c:out value="${type.name}"/></strong>
        </div>

        <jsp:include page="${teamcityPluginResourcesPath}/cargo/${type.viewPage}"/>
    </c:if>
</c:forEach>

<c:if test="${not empty propertiesBean.properties[params.verbosityKey]}">
    <div class="parameter">
        Output verbosity: <props:displayValue name="${params.verbosityKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.toolchainKey]}">
    <div class="parameter">
        Toolchain version: <props:displayValue name="${params.toolchainKey}"/>
    </div>
</c:if>
