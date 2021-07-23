/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.runner.CommandExecution
import jetbrains.buildServer.agent.runner.CommandLineBuildService
import jetbrains.buildServer.agent.runner.ProgramCommandLine
import jetbrains.buildServer.agent.runner.TerminationAction
import java.io.File
import java.util.concurrent.atomic.AtomicReference

class CommandExecutionAdapter(
        private val buildService: CommandLineBuildService,
        private val redirectStderrToStdout: Boolean,
        private val executionResult: AtomicReference<BuildFinishedStatus>
) : CommandExecution {

    private val processListeners by lazy { buildService.listeners }

    override fun processFinished(exitCode: Int) {
        buildService.afterProcessFinished()

        processListeners.forEach {
            it.processFinished(exitCode)
        }

        val result = buildService.getRunResult(exitCode)
        if (result == BuildFinishedStatus.FINISHED_SUCCESS) {
            buildService.afterProcessSuccessfullyFinished()
        }
        executionResult.set(result)
    }

    override fun processStarted(programCommandLine: String, workingDirectory: File) {
        processListeners.forEach {
            it.processStarted(programCommandLine, workingDirectory)
        }
    }

    override fun onStandardOutput(text: String) {
        processListeners.forEach {
            it.onStandardOutput(text)
        }
    }

    override fun onErrorOutput(text: String) {
        processListeners.forEach {
            if (redirectStderrToStdout) {
                it.onStandardOutput(text)
            } else {
                it.onErrorOutput(text)
            }
        }
    }

    override fun interruptRequested(): TerminationAction {
        return buildService.interrupt()
    }

    override fun makeProgramCommandLine(): ProgramCommandLine {
        return buildService.makeProgramCommandLine()
    }

    override fun isCommandLineLoggingEnabled() = buildService.isCommandLineLoggingEnabled

    override fun beforeProcessStarted() {
        buildService.beforeProcessStarted()
    }
}