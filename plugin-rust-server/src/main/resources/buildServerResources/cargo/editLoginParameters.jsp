<%@ page import="jetbrains.buildServer.util.StringUtil" %>
<%@ page import="jetbrains.buildServer.rust.CargoParametersProvider" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<%
    CargoParametersProvider.replaceWithNewParameter(propertiesBean, params.getLoginTokenKey(), params.getLoginTokenKeySecure());
%>

<tr class="advancedSetting">
    <th><label for="${params.loginTokenKeySecure}">Token:</label></th>
    <td>
        <props:passwordProperty name="${params.loginTokenKeySecure}" className="longField"/>
        <span class="error" id="error_${params.loginTokenKeySecure}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.loginHostKey}">Host:</label></th>
    <td>
        <props:textProperty name="${params.loginHostKey}" className="longField"/>
        <span class="error" id="error_${params.loginHostKey}"></span>
        <span class="smallNote">Host to set the token for.</span>
    </td>
</tr>