

package jetbrains.buildServer.rust.commands

/**
 * Provides command-specific resources.
 */
interface CommandType {
    val name: String
    val editPage: String
    val viewPage: String
}