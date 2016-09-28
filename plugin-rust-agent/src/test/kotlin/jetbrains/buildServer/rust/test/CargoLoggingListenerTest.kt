/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

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

/**
 * @author Dmitry.Tretyakov
 * *         Date: 21.06.2016
 * *         Time: 0:05
 */
class CargoLoggingListenerTest {

    @Test(dataProvider = "testTransformations")
    @Throws(IOException::class)
    fun testTransformation(filename: String) {
        val originalFile = File("src/test/resources/cargo/original/" + filename)
        val original = ArrayList(FileUtil.readFile(originalFile))
        val processedFile = File("src/test/resources/cargo/processed/" + filename)
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
            override fun matches(actual: Any): Boolean {
                return processed.size > 0 && isEquals(actual as String, processed.removeAt(0))
            }

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
    fun testTransformations(): Array<Array<Any>> {
        return arrayOf(
                arrayOf<Any>("cargoTests.txt"),
                arrayOf<Any>("cargoCompileVerbose.txt"),
                arrayOf<Any>("cargoTestFailures.txt"))
    }
}
