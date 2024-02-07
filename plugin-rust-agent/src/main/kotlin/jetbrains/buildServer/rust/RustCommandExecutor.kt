

package jetbrains.buildServer.rust

import com.intellij.execution.configurations.GeneralCommandLine
import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.SimpleCommandLineProcessRunner
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReadWriteLock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * Rust command executor.
 */
class RustCommandExecutor : CommandExecutor {
    private val locks = ConcurrentHashMap<String, ReadWriteLock>()

    override fun executeWithReadLock(toolPath: String, arguments: List<String>): String {
        locks.putIfAbsent(toolPath, ReentrantReadWriteLock())
        val readLock = locks[toolPath]!!.readLock()
        readLock.lock()
        try {
            return execute(toolPath, arguments)
        } finally {
            readLock.unlock()
        }
    }

    override fun executeWithWriteLock(toolPath: String, arguments: List<String>): String {
        locks.putIfAbsent(toolPath, ReentrantReadWriteLock())
        val writeLock = locks[toolPath]!!.writeLock()
        writeLock.lock()
        try {
            return execute(toolPath, arguments)
        } finally {
            writeLock.unlock()
        }
    }

    private fun execute(toolPath: String, arguments: List<String>): String {
        val commandLine = GeneralCommandLine().apply {
            exePath = toolPath
            addParameters(arguments)
        }

        val result = SimpleCommandLineProcessRunner.runCommand(commandLine, byteArrayOf())
        if (result.exitCode != 0) {
            throw RunBuildException(result.stderr.trim())
        }

        return result.stdout
    }
}