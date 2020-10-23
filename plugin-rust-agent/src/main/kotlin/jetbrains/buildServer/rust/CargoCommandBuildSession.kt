/*
 * Copyright 2000-2020 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildRunnerContextEx
import jetbrains.buildServer.agent.runner.CommandExecution
import jetbrains.buildServer.agent.runner.CommandLineBuildService
import jetbrains.buildServer.agent.runner.MultiCommandBuildSession
import jetbrains.buildServer.util.FileUtil
import java.io.File

/**
 * Cargo runner service.
 */
class CargoCommandBuildSession(private val runnerContext: BuildRunnerContextEx) : MultiCommandBuildSession {

    private var buildSteps: Iterator<CommandExecution>? = null
    private var lastCommands = arrayListOf<CommandExecutionAdapter>()

    override fun sessionStarted() {
        buildSteps = getSteps()
    }

    override fun getNextCommand(): CommandExecution? {
        buildSteps?.let {
            if (it.hasNext()) {
                return it.next()
            }
        }

        return null
    }

    override fun sessionFinished(): BuildFinishedStatus? {
        return lastCommands.last().result
    }

    private fun getSteps() = iterator<CommandExecution> {
        runnerContext.runnerParameters[CargoConstants.PARAM_TOOLCHAIN]?.let {
            if (it.isNotBlank()) {
                val installToolchain = RustupBuildService("install")
                yield(addCommand(installToolchain))

                // Rustup could fail to install toolchain
                // We could try to resolve it by execution uninstall of toolchain
                // and cleaning up temporary directories
                if (installToolchain.errors.isNotEmpty()) {
                    val logger = runnerContext.build.buildLogger
                    logger.message("Installation has failed, will remove toolchain '${installToolchain.version}' and try again")

                    val uninstallToolchain = RustupBuildService("uninstall")
                    yield(addCommand(uninstallToolchain))

                    val rustupCache = RustupToolProvider.getHome()
                    installToolchain.version.let {
                        // Cleanup temp directories
                        FileUtil.delete(File(rustupCache, CargoConstants.RUSTUP_DOWNLOADS_DIR))
                        FileUtil.delete(File(rustupCache, CargoConstants.RUSTUP_TMP_DIR))

                        // Remove toolchain files
                        FileUtil.delete(File(rustupCache, "${CargoConstants.RUSTUP_TOOLCHAINS_DIR}/$it"))
                        FileUtil.delete(File(rustupCache, "${CargoConstants.RUSTUP_HASHES_DIR}/$it"))
                    }

                    yield(addCommand(RustupBuildService("install")))
                }
            }
        }

        yield(addCommand(CargoRunnerBuildService(runnerContext)))
    }

    private fun addCommand(buildService: CommandLineBuildService) = CommandExecutionAdapter(buildService.apply {
            this.initialize(runnerContext.build, runnerContext)
        }).apply {
            lastCommands.add(this)
        }
}
