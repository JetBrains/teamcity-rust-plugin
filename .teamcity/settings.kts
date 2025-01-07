import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.gradle
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnMetric
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnMetricChange
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {
    description = "https://github.com/JetBrains/teamcity-rust-plugin"

    vcsRoot(HttpsGithubComRustLangFuturesRsGitRefsHeadsMaster1)
    vcsRoot(HttpsGithubComGothamRsGothamGitRefsHeadsMain)

    buildType(Build)
    buildType(FuturesBuild)
    buildType(GothamBuild)
}

object Build : BuildType({
    name = "Build"

    artifactRules = "plugin-rust-server/build/distributions/*.zip"

    params {
        param("system.versionNumber", "%teamcity.build.branch%")
    }

    vcs {
        root(DslContext.settingsRoot)

        checkoutMode = CheckoutMode.ON_AGENT
    }

    steps {
        gradle {
            name = "Build project"
            tasks = "clean build"
            jdkHome = "%env.JDK_18%"
            param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
            buildFile = "build.gradle.kts"
        }
    }

    triggers {
        vcs {
            branchFilter = "+:<default>"
        }
    }

    failureConditions {
        failOnMetricChange {
            enabled = false
            metric = BuildFailureOnMetric.MetricType.ARTIFACT_SIZE
            threshold = 10
            units = BuildFailureOnMetric.MetricUnit.PERCENTS
            comparison = BuildFailureOnMetric.MetricComparison.LESS
            compareTo = build {
                buildRule = lastSuccessful()
            }
        }
        failOnMetricChange {
            metric = BuildFailureOnMetric.MetricType.TEST_COUNT
            threshold = 20
            units = BuildFailureOnMetric.MetricUnit.PERCENTS
            comparison = BuildFailureOnMetric.MetricComparison.LESS
            compareTo = build {
                buildRule = lastSuccessful()
            }
        }
    }
})

object FuturesBuild : BuildType({
    name = "Demo: rust-lang / futures-rs"
    description = "https://github.com/rust-lang/futures-rs"

    vcs {
        root(HttpsGithubComRustLangFuturesRsGitRefsHeadsMaster1)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "test")
            param("cargo-test-features", "default,thread-pool,io-compat")
            param("cargo-test-no-fail-fast", "true")
        }
    }

    triggers {
        vcs { }
    }
})

object GothamBuild : BuildType({
    name = "Demo: gotham-rs / gotham"
    description = "https://github.com/gotham-rs/gotham"

    vcs {
        root(HttpsGithubComGothamRsGothamGitRefsHeadsMain)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "test")
            param("cargo-test-no-fail-fast", "true")
            param("cargo-test-type", "--lib")
        }
    }

    triggers {
        vcs { }
    }
})

object HttpsGithubComRustLangFuturesRsGitRefsHeadsMaster1 : GitVcsRoot({
    name = "https://github.com/rust-lang/futures-rs.git#refs/heads/master"
    url = "https://github.com/rust-lang/futures-rs.git"
    branch = "master"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
})

object HttpsGithubComGothamRsGothamGitRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/gotham-rs/gotham.git#refs/heads/main"
    url = "https://github.com/gotham-rs/gotham.git"
    branch = "main"
    checkoutPolicy = GitVcsRoot.AgentCheckoutPolicy.USE_MIRRORS
})
