plugins {
    kotlin("jvm")
    id("com.github.rodm.teamcity-server") version "1.3.1"
    id("com.github.rodm.teamcity-environments") version "1.3.1"
}

val teamcityVersion = rootProject.extra["teamcityVersion"] as String

teamcity {
    version = teamcityVersion

    server {
        descriptor = project.file("teamcity-plugin.xml")
        tokens = mapOf("Plugin_Version" to "project.version")
    }

    environments {
        downloadsDir = rootProject.extra["downloadsDir"] as String
        baseHomeDir = rootProject.extra["serversDir"] as String
        baseDataDir = "data"

        create("teamcity.2019.2") {
            version = "2019.2"
            javaHome = file(System.getProperty("java.home"))
        }

        create("teamcity2020.1") {
            version = "2020.1.4"
            javaHome = file(System.getProperty("java.home"))
        }
    }
}

dependencies {
    agent(project(path = ":plugin-rust-agent", configuration = "plugin"))

    implementation(project(":plugin-rust-common"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.jetbrains.teamcity.internal:server:$teamcityVersion")
}

tasks.named<Test>("test") {
    useTestNG()
}

tasks.jar {
    archiveVersion.convention(null as String?)
    archiveVersion.set(null as String?)
}

tasks.named<Zip>("serverPlugin") {
    archiveVersion.convention(null as String?)
    archiveVersion.set(null as String?)
    archiveBaseName.set("teamcity-rust-plugin")
}