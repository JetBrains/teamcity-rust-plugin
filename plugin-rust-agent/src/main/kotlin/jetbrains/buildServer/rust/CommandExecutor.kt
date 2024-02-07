

package jetbrains.buildServer.rust

interface CommandExecutor {
    fun executeWithReadLock(toolPath: String, arguments: List<String>): String
    fun executeWithWriteLock(toolPath: String, arguments: List<String>): String
}