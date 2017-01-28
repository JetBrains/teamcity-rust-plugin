/*
 * Copyright 2000-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import com.intellij.openapi.diagnostic.Logger
import jetbrains.buildServer.agent.DirectoryCleanersProvider
import jetbrains.buildServer.agent.DirectoryCleanersProviderContext
import jetbrains.buildServer.agent.DirectoryCleanersRegistry
import java.io.File
import java.util.*

/**
 * Cleans up rust toolchain packages.
 */
class RustupCacheCleaner(toolProvider: RustupToolProvider,
                         private val commandExecutor: RustCommandExecutor)
    : DirectoryCleanersProvider {

    private val rustupPath: String?
    private val rustupCache = File(System.getProperty("user.home"), ".rustup")
    private val LOG = Logger.getInstance(RustupCacheCleaner::class.java.name)

    init {
        rustupPath = try {
            toolProvider.getPath(CargoConstants.RUSTUP_CONFIG_NAME)
        } catch (e: Throwable) {
            LOG.debug("Rustup is not installed")
            null
        }
    }

    override fun getCleanerName() = "${CargoConstants.RUSTUP_CONFIG_NAME} cache cleaner"

    override fun registerDirectoryCleaners(context: DirectoryCleanersProviderContext,
                                           registry: DirectoryCleanersRegistry) {
        rustupPath?.let {
            LOG.info("Registering rust toolchains directory $rustupCache for cleaning")
            registry.addCleaner(rustupCache, Date(), {
                try {
                    commandExecutor.executeWithReadLock(rustupPath, listOf("toolchain", "list"))
                            .lineSequence()
                            .filter { !it.endsWith("(default)") }
                            .forEach {
                                LOG.info("Removing rust toolchain $it")
                                try {
                                    commandExecutor.executeWithWriteLock(rustupPath, listOf("toolchain", "uninstall", it))
                                } catch (e: Throwable) {
                                    LOG.warnAndDebugDetails("Failed to uninstall rust toolchain $it: ${e.message}", e)
                                }
                            }
                } catch (e: Throwable) {
                    LOG.warnAndDebugDetails("Failed to get list of rust toolchains: ${e.message}", e)
                }
            })
        }
    }
}