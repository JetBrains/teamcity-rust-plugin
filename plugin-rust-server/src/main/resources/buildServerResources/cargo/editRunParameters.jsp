<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr>
    <th><label for="${params.runArgumentsKey}">Arguments:</label></th>
    <td>
        <props:textProperty name="${params.runArgumentsKey}" className="longField"/>
        <span class="error" id="error_${params.runArgumentsKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.runTypeKey}">Run:</label></th>
    <td>
        <props:selectProperty name="${params.runTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.runTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.runTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.runReleaseKey}"/>
        <label for="${params.runReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.runFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.runFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.runFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.runNoDefaultFeaturesKey}"/>
        <label for="${params.runNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.runTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.runTargetKey}" className="longField"/>
        <span class="error" id="error_${params.runTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.runManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.runManifestKey}" className="longField"/>
        <span class="error" id="error_${params.runManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.runParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.runParallelKey}" className="longField"/>
        <span class="error" id="error_${params.runParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>