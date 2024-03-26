plugins{
    kotlin("jvm")
    id("io.github.rodm.teamcity-common") version "1.5.3"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

teamcity {
    version = rootProject.extra["teamcityVersion"] as String
}
