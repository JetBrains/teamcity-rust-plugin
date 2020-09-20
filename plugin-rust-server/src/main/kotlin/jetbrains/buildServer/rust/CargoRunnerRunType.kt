/*
 * Copyright 2000-2020 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.requirements.Requirement
import jetbrains.buildServer.requirements.RequirementType
import jetbrains.buildServer.serverSide.PropertiesProcessor
import jetbrains.buildServer.serverSide.RunType
import jetbrains.buildServer.serverSide.RunTypeRegistry
import jetbrains.buildServer.web.openapi.PluginDescriptor

/**
 * Cargo runner definition.
 */
class CargoRunnerRunType(private val myPluginDescriptor: PluginDescriptor,
                         runTypeRegistry: RunTypeRegistry) : RunType() {

    init {
        runTypeRegistry.registerRunType(this)
    }

    override fun getType(): String {
        return CargoConstants.RUNNER_TYPE
    }

    override fun getDisplayName(): String {
        return CargoConstants.RUNNER_DISPLAY_NAME
    }

    override fun getDescription(): String {
        return CargoConstants.RUNNER_DESCRIPTION
    }

    override fun getRunnerPropertiesProcessor(): PropertiesProcessor? {
        return PropertiesProcessor { emptyList() }
    }

    override fun getEditRunnerParamsJspFilePath(): String? {
        return myPluginDescriptor.getPluginResourcesPath("editCargoParameters.jsp")
    }

    override fun getViewRunnerParamsJspFilePath(): String? {
        return myPluginDescriptor.getPluginResourcesPath("viewCargoParameters.jsp")
    }

    override fun getDefaultRunnerProperties(): Map<String, String>? {
        return emptyMap()
    }

    override fun describeParameters(parameters: Map<String, String>): String {
        return "cargo ${parameters[CargoConstants.PARAM_COMMAND]}"
    }

    override fun getRunnerSpecificRequirements(parameters: Map<String, String>): List<Requirement> {
        val toolchainVersion = parameters[CargoConstants.PARAM_TOOLCHAIN]
        return if (toolchainVersion.isNullOrBlank()) {
            listOf(Requirement(CargoConstants.CARGO_CONFIG_PATH, null, RequirementType.EXISTS))
        } else {
            listOf(Requirement(CargoConstants.RUSTUP_CONFIG_PATH, null, RequirementType.EXISTS))
        }
    }
}
