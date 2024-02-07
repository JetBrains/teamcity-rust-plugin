

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