plugins{
    id("com.github.rodm.teamcity-common") version "1.0"
    id("kotlin")
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib")
}

teamcity {
    version = rootProject.extra["teamcityVersion"] as String
}