

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

/**
 * Logger factory.
 */
class CargoLoggerFactory(val logger: BuildProgressLogger) {
    private val myLoggers = mapOf(
            Pair(CargoState.Running, CargoStateLogger(logger, CargoState.Running)),
            Pair(CargoState.Compiling, CargoCompileLogger(logger)),
            Pair(CargoState.Created, CargoStateLogger(logger, CargoState.Created)),
            Pair(CargoState.Finished, CargoStateLogger(logger, CargoState.Finished)),
            Pair(CargoState.Error, CargoStateLogger(logger, CargoState.Error, "ERROR")),
            Pair(CargoState.Warning, CargoStateLogger(logger, CargoState.Warning, "WARNING")),
            Pair(CargoState.Documenting, CargoStateLogger(logger, CargoState.Documenting)),
            Pair(CargoState.Fresh, CargoStateLogger(logger, CargoState.Fresh)),
            Pair(CargoState.Updating, CargoStateLogger(logger, CargoState.Updating)),
            Pair(CargoState.Adding, CargoStateLogger(logger, CargoState.Adding)),
            Pair(CargoState.Removing, CargoStateLogger(logger, CargoState.Removing)),
            Pair(CargoState.DocTests, CargoStateLogger(logger, CargoState.DocTests)),
            Pair(CargoState.Packaging, CargoStateLogger(logger, CargoState.Packaging)),
            Pair(CargoState.Downloading, CargoStateLogger(logger, CargoState.Downloading)),
            Pair(CargoState.Uploading, CargoStateLogger(logger, CargoState.Uploading)),
            Pair(CargoState.Verifying, CargoStateLogger(logger, CargoState.Verifying)),
            Pair(CargoState.Archiving, CargoStateLogger(logger, CargoState.Archiving)),
            Pair(CargoState.Installing, CargoStateLogger(logger, CargoState.Installing)),
            Pair(CargoState.Replacing, CargoStateLogger(logger, CargoState.Replacing)),
            Pair(CargoState.Unpacking, CargoStateLogger(logger, CargoState.Unpacking)),
            Pair(CargoState.Testing, CargoTestingLogger(logger)),
            Pair(CargoState.ErrorDetails, CargoErrorDetailsLogger(logger)))

    fun getLogger(state: CargoState): CargoLogger {
        return myLoggers[state] ?: CargoDefaultLogger(logger)
    }
}