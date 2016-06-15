<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.loginTokenKey]}">
    <div class="parameter">
        Token: <props:displayValue name="${params.loginTokenKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.loginHostKey]}">
    <div class="parameter">
        Host: <props:displayValue name="${params.loginHostKey}"/>
    </div>
</c:if>