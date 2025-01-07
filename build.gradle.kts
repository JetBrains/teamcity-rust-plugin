import java.text.SimpleDateFormat
import java.util.*

repositories {
    mavenCentral()
}

plugins {
    kotlin("jvm") version "1.9.23"
}

val groupId = "teamcity-rust-plugin"
val javaVersion = JavaVersion.VERSION_1_8

extra["teamcityVersion"] = project.findProperty("teamcityVersion") ?: "2020.2"
extra["downloadsDir"] = project.findProperty("downloads.dir") ?: "$rootDir/downloads"
extra["serversDir"] = project.findProperty("servers.dir") ?: "$rootDir/servers"

group = groupId
@Suppress("SpellCheckingInspection")
version = project.findProperty("versionNumber")
    ?.takeIf { Regex("""\d+\.\d+\.\d+.*""").matches(it as String) }
    ?: ("SNAPSHOT-" + SimpleDateFormat("yyyyMMddHHmmss").format(Date()))

allprojects {
    group = groupId

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = javaVersion.toString()
        }
    }
}
