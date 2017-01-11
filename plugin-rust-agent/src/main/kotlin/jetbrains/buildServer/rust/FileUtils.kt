/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import java.io.File

/**
 * File utilities
 */
object FileUtils {

    /**
     * Returns a first matching file in the list of directories.
     *
     * @param paths   list of directories.
     * @param pattern pattern.
     * @return first matching file.
     */
    fun findToolPath(paths: List<String>, pattern: Regex): String? {
        paths
                .map { File(it).listFiles() }
                .filterNotNull()
                .flatMap { it.map { it.absolutePath } }
                .forEach {
                    if (pattern.matches(it)) return it
                }

        return null
    }
}
