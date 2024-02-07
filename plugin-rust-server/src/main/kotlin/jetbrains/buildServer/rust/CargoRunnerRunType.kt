

package jetbrains.buildServer.rust

import jetbrains.buildServer.requirements.Requirement
import jetbrains.buildServer.requirements.RequirementType
import jetbrains.buildServer.serverSide.InvalidProperty
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
        return PropertiesProcessor {
            val errors = mutableListOf<InvalidProperty>()
            val command = it[CargoConstants.PARAM_COMMAND]
            if (command == CargoConstants.COMMAND_CUSTOM_CRATE) {
                val crate = it[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
                if (crate.isNullOrBlank()) {
                    errors.add(InvalidProperty(CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME, "Crate name should not be empty"))
                }
            }
            errors
        }
    }

    override fun getEditRunnerParamsJspFilePath(): String? {
        return myPluginDescriptor.getPluginResourcesPath("editCargoParameters.jsp")
    }

    override fun getViewRunnerParamsJspFilePath(): String? {
        return myPluginDescriptor.getPluginResourcesPath("viewCargoParameters.jsp")
    }

    override fun getDefaultRunnerProperties(): Map<String, String> {
        return emptyMap()
    }

    override fun describeParameters(parameters: Map<String, String>): String {
        val command = parameters[CargoConstants.PARAM_COMMAND]
        if (command == CargoConstants.COMMAND_CUSTOM_CRATE) {
            val crate = parameters[CargoConstants.PARAM_CUSTOM_CRATE_COMMAND_NAME]
            return "cargo $crate"
        }
        return "cargo $command"
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