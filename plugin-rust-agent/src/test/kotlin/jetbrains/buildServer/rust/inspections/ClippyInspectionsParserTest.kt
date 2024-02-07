

package jetbrains.buildServer.rust.inspections

import org.assertj.core.api.Java6Assertions.assertThat
import org.testng.annotations.BeforeMethod

import org.testng.annotations.Test

class ClippyInspectionsParserTest {

    private lateinit var parser: ClippyInspectionsParser

    private lateinit var clippyOutput: String
    private val parserResult = mutableListOf<Inspection>()

    @BeforeMethod
    fun setUp() {
        parser = ClippyInspectionsParser()
    }

    @Test
    fun `should parse empty files without errors`() {
        `given clippy produced`("nothing")
        `when parser runs`()
        `no inspections should be reported`()
    }

    @Test
    fun `should parse clippy output with 1 error reported`() {
        `given clippy produced`("single.error")
        `when parser runs`()
        `single error should be reported`()
    }

    @Test
    fun `should parse clippy output with 1 warning reported`() {
        `given clippy produced`("single.warning")
        `when parser runs`()
        `single warning should be reported`()
    }

    @Test
    fun `should parse clippy output with error and warning reported`() {
        `given clippy produced`("error.and.warning")
        `when parser runs`()
        `error and warning should be reported`()
    }

    @Test
    fun `should parse clippy output with filename with colon symbol in name`() {
        `given clippy produced`("file.with.colon")
        `when parser runs`()
        `error with filename with colon symbol should be reported`()
    }

    private fun `given clippy produced`(filename: String) {
        clippyOutput = this::class.java.classLoader.getResource("cargo/clippy/$filename.txt")!!.readText()
    }

    private fun `when parser runs`() {
        parserResult.clear()
        for (line in clippyOutput.lineSequence()) {
            parser.processLine(line)?.let(parserResult::add)
        }
    }

    private fun `no inspections should be reported`() {
        assertThat(parserResult).isEmpty()
    }

    private fun `single error should be reported`() {
        assertThat(parserResult).hasSize(1)
        val inspection = parserResult[0]
        assertThat(inspection.type).isEqualTo(ClippyInspectionsParser.CLIPPY_ERROR)
        assertThat(inspection.severity).isEqualTo(Inspection.Severity.ERROR)
        assertThat(inspection.message).isEqualTo("for loop over `option`, which is an `Option`. This is more readably written as an `if let` statement")
        assertThat(inspection.file).isEqualTo("clippy2.rs")
        assertThat(inspection.line).isEqualTo(9)
    }

    private fun `single warning should be reported`() {
        assertThat(parserResult).hasSize(1)
        val inspection = parserResult[0]
        assertThat(inspection.type).isEqualTo(ClippyInspectionsParser.CLIPPY_WARNING)
        assertThat(inspection.severity).isEqualTo(Inspection.Severity.WARNING)
        assertThat(inspection.message).isEqualTo("unused variable: `y`")
        assertThat(inspection.file).isEqualTo("clippy.rs")
        assertThat(inspection.line).isEqualTo(16)
    }

    private fun `error and warning should be reported`() {
        assertThat(parserResult).hasSize(2)
        val error = parserResult[0]
        assertThat(error.type).isEqualTo(ClippyInspectionsParser.CLIPPY_ERROR)
        assertThat(error.severity).isEqualTo(Inspection.Severity.ERROR)
        assertThat(error.message).isEqualTo("for loop over `option`, which is an `Option`. This is more readably written as an `if let` statement")
        assertThat(error.file).isEqualTo("clippy2.rs")
        assertThat(error.line).isEqualTo(9)

        val warning = parserResult[1]
        assertThat(warning.type).isEqualTo(ClippyInspectionsParser.CLIPPY_WARNING)
        assertThat(warning.severity).isEqualTo(Inspection.Severity.WARNING)
        assertThat(warning.message).isEqualTo("`assert!(false)` should probably be replaced")
        assertThat(warning.file).isEqualTo("clippy.rs")
        assertThat(warning.line).isEqualTo(19)
    }

    private fun `error with filename with colon symbol should be reported`() {
        assertThat(parserResult).hasSize(1)
        val inspection = parserResult[0]
        assertThat(inspection.type).isEqualTo(ClippyInspectionsParser.CLIPPY_ERROR)
        assertThat(inspection.severity).isEqualTo(Inspection.Severity.ERROR)
        assertThat(inspection.message).isEqualTo("for loop over `option`, which is an `Option`. This is more readably written as an `if let` statement")
        assertThat(inspection.file).isEqualTo("weird:file:name.rs")
        assertThat(inspection.line).isEqualTo(9)
    }
}