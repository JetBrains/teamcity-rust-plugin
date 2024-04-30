package jetbrains.buildServer.rust.test

import jetbrains.buildServer.agent.BuildProgressLogger
import jetbrains.buildServer.rust.logging.CargoLoggerFactory
import jetbrains.buildServer.rust.logging.CargoLoggingListener
import jetbrains.buildServer.util.FileUtil
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.jmock.Expectations
import org.jmock.Mockery
import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import java.io.File
import java.io.IOException
import java.util.*

class CargoLoggingListenerTest {
    @Test(dataProvider = "testTransformations")
    @Throws(IOException::class)
    fun testTransformation(filename: String) {
        val originalFile = File("src/test/resources/cargo/original/$filename")
        val original = ArrayList(FileUtil.readFile(originalFile))
        val processedFile = File("src/test/resources/cargo/processed/$filename")
        val processed = ArrayList(FileUtil.readFile(processedFile))
        val m = Mockery()
        val logger = m.mock<BuildProgressLogger>(BuildProgressLogger::class.java)
        val matcher = getMatcher(processed)
        val numberOfLines = processed.size
        m.checking(object : Expectations() {
            init {
                exactly(numberOfLines).of(logger).message(with(matcher))
            }
        })

        val loggerFactory = CargoLoggerFactory(logger)
        val listener = CargoLoggingListener(loggerFactory)

        for (message in original) {
            listener.onStandardOutput(message)
        }

        listener.processFinished(0)

        m.assertIsSatisfied()
    }

    private fun getMatcher(processed: MutableList<String>): BaseMatcher<String> {
        return object : BaseMatcher<String>() {
            override fun matches(actual: Any): Boolean =
                processed.isNotEmpty() && isEquals(actual as String, processed.removeAt(0))

            override fun describeTo(description: Description) {
                description.appendText("List matcher")
            }
        }
    }

    private fun isEquals(actual: String, expected: String): Boolean {
        val cleaned = actual.replace("\\s(duration='\\d+')".toRegex(), "")
        return cleaned == expected
    }

    @DataProvider
    fun testTransformations(): Array<Array<Any>> = arrayOf(
        arrayOf("cargoTests.txt"),
        arrayOf("cargoCompileVerbose.txt"),
        arrayOf("cargoTestFailures.txt"),
        arrayOf("cargoBuild.txt"),
        arrayOf("cargoWarnings.txt"),
        arrayOf("cargoDocTests1.txt"),
        arrayOf("cargoDocTests2.txt"),
        arrayOf("cargoDocTests3.txt"),
        arrayOf("cargoDocTests4.txt"),
        arrayOf("cargoFeatureError.txt")
    )
}
