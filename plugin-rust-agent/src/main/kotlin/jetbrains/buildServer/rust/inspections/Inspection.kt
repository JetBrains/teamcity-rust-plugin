/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.rust.inspections

import jetbrains.buildServer.agent.inspections.InspectionSeverityValues
import jetbrains.buildServer.agent.inspections.InspectionTypeInfo

data class Inspection(
    val type: InspectionTypeInfo,
    val message: String,
    val file: String,
    val line: Int,
    val severity: Severity
) {
    enum class Severity(val type: String) {
        ERROR(InspectionSeverityValues.ERROR.toString()),
        WARNING(InspectionSeverityValues.WARNING.toString());
    }
}
