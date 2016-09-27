/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import jetbrains.buildServer.agent.BuildProgressLogger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitry.Tretyakov
 *         Date: 25.09.2016
 *         Time: 21:49
 */
public class CargoLoggerFactory {
    private final Map<CargoState, CargoLogger> myLoggers;

    public CargoLoggerFactory(@NotNull final BuildProgressLogger logger){
        myLoggers = new HashMap<CargoState, CargoLogger>();
        myLoggers.put(CargoState.Running, new CargoStateLogger(logger, CargoState.Running));
        myLoggers.put(CargoState.Compiling, new CargoCompileLogger(logger));
        myLoggers.put(CargoState.Error, new CargoStateLogger(logger, CargoState.Error));
        myLoggers.put(CargoState.Warning, new CargoStateLogger(logger, CargoState.Warning));
        myLoggers.put(CargoState.Documenting, new CargoStateLogger(logger, CargoState.Documenting));
        myLoggers.put(CargoState.Fresh, new CargoStateLogger(logger, CargoState.Fresh));
        myLoggers.put(CargoState.Updating, new CargoStateLogger(logger, CargoState.Updating));
        myLoggers.put(CargoState.Adding, new CargoStateLogger(logger, CargoState.Adding));
        myLoggers.put(CargoState.Removing, new CargoStateLogger(logger, CargoState.Removing));
        myLoggers.put(CargoState.DocTests, new CargoStateLogger(logger, CargoState.DocTests));
        myLoggers.put(CargoState.Packaging, new CargoStateLogger(logger, CargoState.Packaging));
        myLoggers.put(CargoState.Downloading, new CargoStateLogger(logger, CargoState.Downloading));
        myLoggers.put(CargoState.Uploading, new CargoStateLogger(logger, CargoState.Uploading));
        myLoggers.put(CargoState.Verifying, new CargoStateLogger(logger, CargoState.Verifying));
        myLoggers.put(CargoState.Archiving, new CargoStateLogger(logger, CargoState.Archiving));
        myLoggers.put(CargoState.Installing, new CargoStateLogger(logger, CargoState.Installing));
        myLoggers.put(CargoState.Replacing, new CargoStateLogger(logger, CargoState.Replacing));
        myLoggers.put(CargoState.Default, new CargoDefaultLogger(logger));
        myLoggers.put(CargoState.Testing, new CargoTestingLogger(logger));
    }

    @NotNull
    public CargoLogger getLogger(final CargoState state) {
        return myLoggers.get(state);
    }
}
