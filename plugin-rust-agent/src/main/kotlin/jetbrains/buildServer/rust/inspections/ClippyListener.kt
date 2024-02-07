

package jetbrains.buildServer.rust.inspections

import jetbrains.buildServer.agent.inspections.InspectionAttributesId
import jetbrains.buildServer.agent.inspections.InspectionInstance
import jetbrains.buildServer.agent.inspections.InspectionReporter
import jetbrains.buildServer.agent.runner.ProcessListenerAdapter

class ClippyListener(
    private val inspectionsReporter: InspectionReporter,
    private val clippyInspectionsParser: ClippyInspectionsParser
) : ProcessListenerAdapter() {
    init {
        inspectionsReporter.reportInspectionType(ClippyInspectionsParser.CLIPPY_ERROR)
        inspectionsReporter.reportInspectionType(ClippyInspectionsParser.CLIPPY_WARNING)
    }

    override fun onStandardOutput(text: String) {
        onLineOutput(text)
    }

    override fun onErrorOutput(text: String) {
        onLineOutput(text)
    }

    private fun onLineOutput(text: String) {
        val inspection = clippyInspectionsParser.processLine(text)
        if (inspection != null) {
            inspectionsReporter.reportInspection(InspectionInstance().apply {
                inspectionId = inspection.type.id
                message = inspection.message
                line = inspection.line
                filePath = inspection.file
                addAttribute(InspectionAttributesId.SEVERITY.toString(), listOf(inspection.severity.type))
            })
        }
    }
}