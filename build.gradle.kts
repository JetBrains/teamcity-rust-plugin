import java.text.SimpleDateFormat
import java.util.*

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.72")
    }
}

val groupId = "teamcity-rust-plugin"

val pluginVersionDateFormat = SimpleDateFormat("yyyyMMddHHmmss")
val correctVersion = project.hasProperty("versionNumber") &&
        Regex("""\d+\.\d+\.\d+.*""").matches(property("versionNumber") as String)
val versionNumber = if (correctVersion) {
    property("versionNumber")!!
} else {
    "SNAPSHOT-" + pluginVersionDateFormat.format(Date())
}

extra["teamcityVersion"] = if (project.hasProperty("teamcityVersion")) {
    property("teamcityVersion")
} else {
    "2018.1"
}

extra["downloadsDir"] = project.findProperty("downloads.dir") ?: "$rootDir/downloads"
extra["serversDir"] = project.findProperty("servers.dir") ?: "$rootDir/servers"

group = groupId
version = versionNumber

allprojects {
    group = groupId
}
