<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.customCrateCommandName]}">
    <div class="parameter">
        Crate name: <props:displayValue name="${params.customCrateCommandName}"/>
    </div>
</c:if>
<c:if test="${not empty propertiesBean.properties[params.customCrateCommandVersion]}">
    <div class="parameter">
        Crate version: <props:displayValue name="${params.customCrateCommandVersion}"/>
    </div>
</c:if>
