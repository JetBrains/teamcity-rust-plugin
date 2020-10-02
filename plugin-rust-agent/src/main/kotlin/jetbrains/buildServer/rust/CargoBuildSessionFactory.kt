/*
 * Copyright 2000-2020 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust

import jetbrains.buildServer.agent.AgentBuildRunnerInfo
import jetbrains.buildServer.agent.BuildAgentConfiguration
import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.agent.runner.MultiCommandBuildSessionFactory

/**
 * Cargo runner service factory.
 */
class CargoBuildSessionFactory : MultiCommandBuildSessionFactory {

    override fun createSession(runnerContext: BuildRunnerContext) = CargoCommandBuildSession(runnerContext)

    override fun getBuildRunnerInfo(): AgentBuildRunnerInfo {
        return object : AgentBuildRunnerInfo {
            override fun getType(): String {
                return CargoConstants.RUNNER_TYPE
            }

            override fun canRun(config: BuildAgentConfiguration): Boolean {
                return true
            }
        }
    }
}
