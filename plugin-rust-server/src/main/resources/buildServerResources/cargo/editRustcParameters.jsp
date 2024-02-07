<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>


<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr class="advancedSetting">
    <th><label for="${params.rustcOptsKey}">Options:</label></th>
    <td>
        <props:textProperty name="${params.rustcOptsKey}" className="longField"/>
        <span class="error" id="error_${params.rustcOptsKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.rustcPackageKey}" className="longField"/>
        <span class="error" id="error_${params.rustcPackageKey}"></span>
        <span class="smallNote">The profile to compile for.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcTypeKey}">Build:</label></th>
    <td>
        <props:selectProperty name="${params.rustcTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.rustcTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.rustcTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.rustcReleaseKey}"/>
        <label for="${params.rustcReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.rustcFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.rustcFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.rustcNoDefaultFeaturesKey}"/>
        <label for="${params.rustcNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.rustcTargetKey}" className="longField"/>
        <span class="error" id="error_${params.rustcTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.rustcManifestKey}" className="longField"/>
        <span class="error" id="error_${params.rustcManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.rustcParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.rustcParallelKey}" className="longField"/>
        <span class="error" id="error_${params.rustcParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>