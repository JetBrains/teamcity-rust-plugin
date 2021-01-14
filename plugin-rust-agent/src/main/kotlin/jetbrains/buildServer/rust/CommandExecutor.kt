/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

interface CommandExecutor {
    fun executeWithReadLock(toolPath: String, arguments: List<String>): String
    fun executeWithWriteLock(toolPath: String, arguments: List<String>): String
}