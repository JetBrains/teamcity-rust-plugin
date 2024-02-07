

package jetbrains.buildServer.rust.inspections

import jetbrains.buildServer.agent.inspections.InspectionTypeInfo

class ClippyInspectionsParser {
    private lateinit var currentType: InspectionTypeInfo
    private lateinit var currentMessage: String

    companion object {
        val CLIPPY_WARNING = InspectionTypeInfo().apply {
            id = "rust-inspection-clippy-warning"
            description = "Clippy Warning"
            category = "Clippy Inspection"
            name = "Clippy Warning"
        }

        val CLIPPY_ERROR = InspectionTypeInfo().apply {
            id = "rust-inspection-clippy-error"
            description = "Clippy Error"
            category = "Clippy Inspection"
            name = "Clippy Error"
        }
    }

    fun processLine(text: String): Inspection? {
        return when {
            text.startsWith("error: ") -> {
                currentType = CLIPPY_ERROR
                currentMessage = text.removePrefix("error: ")
                null
            }
            text.startsWith("warning: ") -> {
                currentType = CLIPPY_WARNING
                currentMessage = text.removePrefix("warning: ")
                null
            }
            text.matches("^\\s+--> .+".toRegex()) -> {
                val position = text.replace("^\\s+--> ".toRegex(), "")
                val split = position.split(":")
                val filename = split.subList(0, split.size-2).joinToString(":")
                val line = split[split.size-2]

                Inspection(
                    type = currentType,
                    message = currentMessage,
                    file = filename,
                    line = line.toInt(),
                    severity = if (currentType == CLIPPY_ERROR) Inspection.Severity.ERROR else Inspection.Severity.WARNING
                )
            }
            else -> null
        }
    }
}