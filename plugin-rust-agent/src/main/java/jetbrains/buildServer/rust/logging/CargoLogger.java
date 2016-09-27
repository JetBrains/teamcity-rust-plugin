/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.logging;

import org.jetbrains.annotations.NotNull;

/**
 * Cargo logger.
 */
public interface CargoLogger {
    void onEnter(@NotNull final String text);
    void processLine(@NotNull final String text);
    void processError(@NotNull final String text);
    void onLeave();
    boolean canChangeState(CargoState state, String text);
}
