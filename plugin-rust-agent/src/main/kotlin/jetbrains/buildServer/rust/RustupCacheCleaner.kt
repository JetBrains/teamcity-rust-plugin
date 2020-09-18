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
import jetbrains.buildServer.util.FileUtil
import java.io.File
import java.util.*

/**
 * Cleans up rust toolchain packages.
 */
class RustupCacheCleaner(toolProvider: RustupToolProvider,
                         private val commandExecutor: RustCommandExecutor)
    : DirectoryCleanersProvider {

    private val rustupPath: String?
    private val rustupCache = RustupToolProvider.getHome()
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

            registry.addCleaner(rustupCache, Date()) {
                try {
                    commandExecutor.executeWithReadLock(rustupPath, listOf("toolchain", "list"))
                            .lineSequence()
                            .filter { it.isNotBlank() && !it.endsWith("(default)") }
                            .forEach {
                                LOG.info("Removing rust toolchain $it")
                                try {
                                    commandExecutor.executeWithWriteLock(rustupPath, listOf("toolchain", "uninstall", it))
                                } catch (e: Throwable) {
                                    LOG.warnAndDebugDetails("Failed to uninstall rust toolchain $it: ${e.message}", e)
                                    LOG.info("Will try to remove toolchain $it automatically")

                                    val toolchainDirectory = File(rustupCache, "${CargoConstants.RUSTUP_TOOLCHAINS_DIR}/$it")
                                    if (!FileUtil.delete(toolchainDirectory)) {
                                        LOG.warn("Failed to delete directory: $toolchainDirectory")
                                    }

                                    val toolchainFile = File(rustupCache, "${CargoConstants.RUSTUP_HASHES_DIR}/$it")
                                    if (!FileUtil.delete(toolchainFile)) {
                                        LOG.warn("Failed to delete file: $toolchainFile")
                                    }
                                }
                            }
                } catch (e: Throwable) {
                    LOG.warnAndDebugDetails("Failed to get list of rust toolchains: ${e.message}", e)
                }
            }

            val downloads = File(rustupCache, CargoConstants.RUSTUP_DOWNLOADS_DIR)
            LOG.info("Registering directory $downloads for cleaning")
            registry.addCleaner(downloads, Date())

            val tmp = File(rustupCache, CargoConstants.RUSTUP_TMP_DIR)
            LOG.info("Registering directory $tmp for cleaning")
            registry.addCleaner(tmp, Date())
        }
    }
}