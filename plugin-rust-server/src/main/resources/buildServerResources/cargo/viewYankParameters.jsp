<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<c:if test="${not empty propertiesBean.properties[params.yankCrateKey]}">
    <div class="parameter">
        Crate: <props:displayValue name="${params.yankCrateKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.yankVersionKey]}">
    <div class="parameter">
        Version: <props:displayValue name="${params.yankVersionKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.yankUndoKey]}">
    <div class="parameter">
        Undo a yank, putting a version back into the index: <strong>ON</strong>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.yankIndexKey]}">
    <div class="parameter">
        Index: <props:displayValue name="${params.yankIndexKey}"/>
    </div>
</c:if>

<c:if test="${not empty propertiesBean.properties[params.yankTokenKey]}">
    <div class="parameter">
        Token: <props:displayValue name="${params.yankTokenKey}"/>
    </div>
</c:if>