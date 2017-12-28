package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import java.io.File

class BlockListener(private val blockName:String,
                    private val logger: BuildProgressLogger) : ProcessListenerAdapter() {

    override fun processStarted(programCommandLine: String, workingDirectory: File) {
        logger.message("##teamcity[blockOpened name='$blockName']")
    }

    override fun processFinished(exitCode: Int) {
        logger.message("##teamcity[blockClosed name='$blockName']")
    }
}