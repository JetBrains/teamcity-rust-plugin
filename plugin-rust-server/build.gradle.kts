plugins {
    kotlin("jvm")
    id("io.github.rodm.teamcity-server") version "1.5.3"
    id("io.github.rodm.teamcity-environments") version "1.5.3"
}

val teamcityVersion = rootProject.extra["teamcityVersion"] as String

teamcity {
    version = teamcityVersion

    server {
        archiveName = "teamcity-rust-plugin"
        descriptor = project.file("teamcity-plugin.xml")
        tokens = mapOf("Plugin_Version" to rootProject.version)

        files {
            into("kotlin-dsl") {
                from("kotlin-dsl")
            }
        }
    }

    environments {
        downloadsDir = rootProject.extra["downloadsDir"] as String
        baseHomeDir = rootProject.extra["serversDir"] as String
        baseDataDir = "data"

        create("teamcity2020.2") {
            version = "2020.2"
            javaHome = System.getProperty("java.home")
        }
        create("teamcity2021.1") {
            version = "2021.1"
            javaHome = System.getProperty("java.home")
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
