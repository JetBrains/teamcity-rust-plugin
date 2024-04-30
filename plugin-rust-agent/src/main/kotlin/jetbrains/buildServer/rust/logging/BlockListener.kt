package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter
import java.io.File

private const val SCRIPTLET_START = "sh -c "
private const val SCRIPTLET_END = " 2>&1"

class BlockListener(private val blockName: String, private val logger: BuildProgressLogger) : ProcessListenerAdapter() {
    override fun processStarted(programCommandLine: String, workingDirectory: File) {
        logger.message("##teamcity[blockOpened name='$blockName']")

        // quoting `sh -c` scriptlet argument to make it look more natural in the output
        val start = programCommandLine.indexOf(SCRIPTLET_START)
        val end = programCommandLine.indexOf(SCRIPTLET_END)
        val quotedCommandLine = if (start >= 0 && end > start) {
            programCommandLine.substring(0, start + SCRIPTLET_START.length) +
            '"' + programCommandLine.substring(start + SCRIPTLET_START.length, end + SCRIPTLET_END.length) + '"' +
            programCommandLine.substring(end + SCRIPTLET_END.length)
        } else programCommandLine
        logger.message("starting: $quotedCommandLine")

        logger.message("in directory: $workingDirectory")
    }

    override fun processFinished(exitCode: Int) {
        logger.message("##teamcity[blockClosed name='$blockName']")
    }
}
