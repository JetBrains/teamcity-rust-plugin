<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="params" class="jetbrains.buildServer.rust.CargoParametersProvider"/>

<tr>
    <th><label for="${params.testArgumentsKey}">Arguments:</label></th>
    <td>
        <props:textProperty name="${params.testArgumentsKey}" className="longField"/>
        <span class="error" id="error_${params.testArgumentsKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testPackageKey}">Package:</label></th>
    <td>
        <props:textProperty name="${params.testPackageKey}" className="longField"/>
        <span class="error" id="error_${params.testPackageKey}"></span>
        <span class="smallNote">Package to run tests for.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testTypeKey}">Test:</label></th>
    <td>
        <props:selectProperty name="${params.testTypeKey}" enableFilter="true" className="smallField">
            <props:option value="">&lt;Auto&gt;</props:option>
            <props:option value="--lib">Library</props:option>
            <props:option value="--bin">Binary</props:option>
            <props:option value="--example">Example</props:option>
            <props:option value="--test">Test</props:option>
            <props:option value="--bench">Benchmark</props:option>
        </props:selectProperty>
        <span>name:</span>
        <props:textProperty name="${params.testTypeNameKey}" className="mediumField"/>
        <span class="error" id="error_${params.testTypeNameKey}"></span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.testReleaseKey}"/>
        <label for="${params.testReleaseKey}">Build artifacts in release mode, with optimizations</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.testNoRunKey}"/>
        <label for="${params.testNoRunKey}">Compile, but do not run tests</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.testNoFailFastKey}"/>
        <label for="${params.testNoFailFastKey}">Run all tests regardless of failure</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testFeaturesKey}">Features:</label></th>
    <td>
        <props:textProperty name="${params.testFeaturesKey}" className="longField"/>
        <span class="error" id="error_${params.testFeaturesKey}"></span>
        <span class="smallNote">Space-separated list of features to also build.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th class="noBorder"></th>
    <td>
        <props:checkboxProperty name="${params.testNoDefaultFeaturesKey}"/>
        <label for="${params.testNoDefaultFeaturesKey}">Do not build default features</label>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testTargetKey}">Target:</label></th>
    <td>
        <props:textProperty name="${params.testTargetKey}" className="longField"/>
        <span class="error" id="error_${params.testTargetKey}"></span>
        <span class="smallNote">Build for the target triple.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testManifestKey}">Manifest:</label></th>
    <td>
        <props:textProperty name="${params.testManifestKey}" className="longField"/>
        <span class="error" id="error_${params.testManifestKey}"></span>
        <span class="smallNote">Path to the manifest to compile.</span>
    </td>
</tr>

<tr class="advancedSetting">
    <th><label for="${params.testParallelKey}">Parallel jobs:</label></th>
    <td>
        <props:textProperty name="${params.testParallelKey}" className="longField"/>
        <span class="error" id="error_${params.testParallelKey}"></span>
        <span class="smallNote">The number of jobs to run in parallel.</span>
    </td>
</tr>