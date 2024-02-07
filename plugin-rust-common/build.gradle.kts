

plugins{
    id("com.github.rodm.teamcity-common") version "1.3.1"
    id("kotlin")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}

teamcity {
    version = rootProject.extra["teamcityVersion"] as String
}