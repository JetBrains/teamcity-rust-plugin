# TeamCity Rust Plugin

<a href="https://teamcity.jetbrains.com/viewType.html?buildTypeId=TeamCityRustPluginBuild&guest=1"><img src="https://teamcity.jetbrains.com/app/rest/builds/buildType:(id:TeamCityRustPluginBuild)/statusIcon.svg" alt=""/></a>

TeamCity Rust plugin brings build infrastructure support for [Rust](https://www.rust-lang.org/) programming language.

# Features

It provides following features for rust projects:
* Cargo command build runner
* Cargo tests reporter
* Structured build log listener
 
# Download

You can download plugin from the [last successful build](https://teamcity.jetbrains.com/repository/download/TeamCityRustPluginBuild/.lastSuccessful/teamcity-rust-plugin.zip?guest=1) and install it as [additional TeamCity plugin](https://confluence.jetbrains.com/display/TCDL/Installing+Additional+Plugins).

# Compatibility

Plugin is compatible with [TeamCity](https://www.jetbrains.com/teamcity/download/) 9.1.x and greater.

# Build

This project uses gradle as a build system. You can easily open it in [IntelliJ IDEA](https://www.jetbrains.com/idea/help/importing-project-from-gradle-model.html) or [Eclipse](http://gradle.org/eclipse/).

# Contributions

We appreciate all kinds of feedback, so please feel free to send a PR or write an issue.
