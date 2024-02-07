

plugins {
    id("kotlin")
    id("com.github.rodm.teamcity-agent") version "1.3.1"
}

val teamcityVersion = rootProject.extra["teamcityVersion"] as String

teamcity {
    version = teamcityVersion
    agent {
        archiveName = project.name
        descriptor = project.file("teamcity-plugin.xml")
    }
}

dependencies {
    implementation(project(":plugin-rust-common"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.zafarkhaja:java-semver:0.9.0")
    compileOnly("org.jetbrains.teamcity.internal:agent:${teamcityVersion}")
    testImplementation("org.testng:testng:6.8")
    testImplementation("org.jmock:jmock:2.5.1")
    testImplementation("org.assertj:assertj-core:3.11.1")
}

tasks.named<Test>("test") {
    useTestNG()
}