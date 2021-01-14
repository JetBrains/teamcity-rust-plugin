/*
 * Copyright 2000-2021 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

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

