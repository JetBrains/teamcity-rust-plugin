<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.buildPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.buildPackageKey}" className="longField"/>
        <span class="error" id="error_${params.buildPackageKey}"></span>
        <span class="smallNote">Package to build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.buildTypeKey}">Build:</label></th>
    <td>
        <props:selectProperty name="${params.buildTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.buildTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.buildTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.buildReleaseKey}"/>
        <label for="${params.buildReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.buildFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.buildFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.buildFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.buildNoDefaultFeaturesKey}"/>
        <label for="${params.buildNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.buildTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.buildTargetKey}" className="longField"/>
        <span class="error" id="error_${params.buildTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.buildManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.buildManifestKey}" className="longField"/>
        <span class="error" id="error_${params.buildManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.buildParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.buildParallelKey}" className="longField"/>
        <span class="error" id="error_${params.buildParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>