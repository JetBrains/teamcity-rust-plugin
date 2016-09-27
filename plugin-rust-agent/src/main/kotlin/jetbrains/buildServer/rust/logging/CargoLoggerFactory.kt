/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging

import jetbrains.buildServer.agent.BuildProgressLogger

import java.util.HashMap

/**
 * Logger factory.
 */
class CargoLoggerFactory(logger: BuildProgressLogger) {
    private val myLoggers: MutableMap<CargoState, CargoLogger>

    init {
        myLoggers = HashMap<CargoState, CargoLogger>()
        myLoggers.put(CargoState.Running, CargoStateLogger(logger, CargoState.Running))
        myLoggers.put(CargoState.Compiling, CargoCompileLogger(logger))
        myLoggers.put(CargoState.Error, CargoStateLogger(logger, CargoState.Error))
        myLoggers.put(CargoState.Warning, CargoStateLogger(logger, CargoState.Warning))
        myLoggers.put(CargoState.Documenting, CargoStateLogger(logger, CargoState.Documenting))
        myLoggers.put(CargoState.Fresh, CargoStateLogger(logger, CargoState.Fresh))
        myLoggers.put(CargoState.Updating, CargoStateLogger(logger, CargoState.Updating))
        myLoggers.put(CargoState.Adding, CargoStateLogger(logger, CargoState.Adding))
        myLoggers.put(CargoState.Removing, CargoStateLogger(logger, CargoState.Removing))
        myLoggers.put(CargoState.DocTests, CargoStateLogger(logger, CargoState.DocTests))
        myLoggers.put(CargoState.Packaging, CargoStateLogger(logger, CargoState.Packaging))
        myLoggers.put(CargoState.Downloading, CargoStateLogger(logger, CargoState.Downloading))
        myLoggers.put(CargoState.Uploading, CargoStateLogger(logger, CargoState.Uploading))
        myLoggers.put(CargoState.Verifying, CargoStateLogger(logger, CargoState.Verifying))
        myLoggers.put(CargoState.Archiving, CargoStateLogger(logger, CargoState.Archiving))
        myLoggers.put(CargoState.Installing, CargoStateLogger(logger, CargoState.Installing))
        myLoggers.put(CargoState.Replacing, CargoStateLogger(logger, CargoState.Replacing))
        myLoggers.put(CargoState.Default, CargoDefaultLogger(logger))
        myLoggers.put(CargoState.Testing, CargoTestingLogger(logger))
    }

    fun getLogger(state: CargoState): CargoLogger {
        return myLoggers[state]!!
    }
}
