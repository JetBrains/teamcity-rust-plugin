plugins {
    id("kotlin")
    id("com.github.rodm.teamcity-agent") version "1.3"
}

val teamcityVersion = rootProject.extra["teamcityVersion"] as String

teamcity {
    version = teamcityVersion
    agent {
        descriptor = project.file("teamcity-plugin.xml")
    }
}

dependencies {
    implementation(project(":plugin-rust-common"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("com.github.zafarkhaja:java-semver:0.9.0")
    provided("org.jetbrains.teamcity.internal:agent:${teamcityVersion}")
    testImplementation("org.testng:testng:6.8")
    testImplementation("org.jmock:jmock:2.5.1")
}

tasks.named<Test>("test") {
    useTestNG()
}

tasks.jar {
    archiveVersion.convention(null as String?)
    archiveVersion.set(null as String?)
}

tasks.named<Zip>("agentPlugin") {
    archiveVersion.convention(null as String?)
    archiveVersion.set(null as String?)
}