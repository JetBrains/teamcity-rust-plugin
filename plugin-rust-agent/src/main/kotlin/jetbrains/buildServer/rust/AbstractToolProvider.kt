/*
 * Copyright 2000-2024 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */



package jetbrains.buildServer.rust

import com.github.zafarkhaja.semver.Version
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.SimpleCommandLineProcessRunner
import jetbrains.buildServer.agent.*
import jetbrains.buildServer.util.EventDispatcher
import jetbrains.buildServer.util.StringUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import kotlin.io.path.absolutePathString

/**
 * Determines tool location.
 */
abstract class AbstractToolProvider(
        toolsRegistry: ToolProvidersRegistry,
        events: EventDispatcher<AgentLifeCycleListener>,
        private val configName: String,
        private val configPath: String,
        private val configExecutableName: String
): AgentLifeCycleAdapter(), ToolProvider {
    private val LOG = Logger.getInstance(AbstractToolProvider::class.java.name)
    private val VERSION_PATTERN = Regex("^$configName[\\s-]([^\\s]+)", RegexOption.IGNORE_CASE)
    private val FILE_NAME_PATH_FILTER: (Path) -> Boolean = { path: Path ->
        path.fileName.toString().lowercase().removeSuffix(".exe") == configExecutableName
    }

    init {
        require(configExecutableName.lowercase() == configExecutableName)
        toolsRegistry.registerToolProvider(this)
        events.addListener(this)
    }

    override fun beforeAgentConfigurationLoaded(agent: BuildAgent) {
        LOG.info("Locating $configName tool")
        findToolPath()?.let {
            LOG.info("Found $configName at ${it.first}")
            agent.configuration.apply {
                addConfigurationParameter(configPath, it.first)
                addConfigurationParameter(configName, it.second.toString())
            }
        }
    }

    override fun supports(toolName: String): Boolean {
        return configName.equals(toolName, true)
    }

    override fun getPath(toolName: String): String {
        if (!supports(toolName)) throw ToolCannotBeFoundException("Unsupported tool $toolName")

        findToolPath()?.let {
            return it.first
        }

        throw ToolCannotBeFoundException("""
                Unable to locate tool $toolName in system. Please make sure to add it in the PATH variable
                """.trimIndent())
    }

    override fun getPath(toolName: String,
                         build: AgentRunningBuild,
                         runner: BuildRunnerContext): String {
        if (runner.isVirtualContext) {
            return configExecutableName
        }

        return build.agentConfiguration.configurationParameters[configPath] ?: getPath(toolName)
    }

    /**
     * Returns a first matching file in the list of directories.
     *
     * @return first matching file.
     */
    private fun findToolPath(): Pair<String, Version>? {
        val paths = LinkedHashSet<String>().apply {
            System.getenv(CargoConstants.ENV_CARGO_HOME)?.let {
                this.add(it + File.separatorChar + "bin")
            }
            System.getenv("PATH")?.let {
                this.addAll(StringUtil.splitHonorQuotes(it, File.pathSeparatorChar))
            }
            System.getProperty("user.home")?.let {
                this.add(it + File.separatorChar + ".cargo" + File.separatorChar + "bin")
            }
        }

        return paths.map { Path.of(it) }
            .filter { Files.isDirectory(it) }
            .flatMap { dir ->
                try {
                    Files.newDirectoryStream(dir, FILE_NAME_PATH_FILTER).use { stream -> stream.toList() }
                } catch (_: Throwable) {
                    emptyList<Path>()
                }
            }
            .map { it.absolutePathString() }
            .mapNotNull {
                    try {
                        val commandLine = getVersionCommandLine(it)
                        val result = SimpleCommandLineProcessRunner.runCommand(commandLine, byteArrayOf())
                        val version = VERSION_PATTERN.find(result.stdout)?.groups?.get(1)?.value ?: result.stdout
                        it to Version.valueOf(version)
                    } catch (e: Throwable) {
                        LOG.warnAndDebugDetails("Failed to parse $configName version: ${e.message}", e)
                        null
                    }
                }.maxByOrNull { it.second }
    }

    private fun getVersionCommandLine(toolPath: String): GeneralCommandLine {
        val commandLine = GeneralCommandLine()
        commandLine.exePath = toolPath
        commandLine.addParameter("--version")
        return commandLine
    }
}