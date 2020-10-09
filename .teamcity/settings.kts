import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.commitStatusPublisher
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

    vcsRoot(HttpsGithubComHyperiumHyperGitRefsHeadsMas)
    vcsRoot(HttpsGithubComAlexcrichtonFuturesRsGitRefs)
    vcsRoot(HttpsGithubComCarllercheMioGitRefsHeadsMas)
    vcsRoot(HttpsGithubComSergioBenitezRocketGitRefsHe)
    vcsRoot(HttpsGithubComDieselRsDieselGitRefsHeadsMa)
    vcsRoot(HttpsGithubComGothamRsGothamGitRefsHeadsMa)
    vcsRoot(HttpsGithubComIronIronGitRefsHeadsMaster)

    buildType(DieselBuild)
    buildType(IronBuild)
    buildType(MioBuild)
    buildType(GothamBuild)
    buildType(FuturesBuild)
    buildType(HyperBuild)
    buildType(Build)
    buildType(RocketBuild)
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
            branchFilter = ""
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

    features {
        commitStatusPublisher {
            vcsRootExtId = "411"
            publisher = github {
                githubUrl = "https://api.github.com"
                authType = personalToken {
                    token = "credentialsJSON:1f157e28-9566-463a-a597-06d7bc6b8ed2"
                }
            }
            param("github_oauth_user", "missingdays")
        }
    }
})

object DieselBuild : BuildType({
    name = "Diesel Build"

    vcs {
        root(HttpsGithubComDieselRsDieselGitRefsHeadsMa)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object FuturesBuild : BuildType({
    name = "Futures Build"

    vcs {
        root(HttpsGithubComAlexcrichtonFuturesRsGitRefs)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object GothamBuild : BuildType({
    name = "Gotham Build"

    vcs {
        root(HttpsGithubComGothamRsGothamGitRefsHeadsMa)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-test-type", "--lib")
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object HyperBuild : BuildType({
    name = "Hyper Build"

    vcs {
        root(HttpsGithubComHyperiumHyperGitRefsHeadsMas)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object IronBuild : BuildType({
    name = "Iron Build"

    vcs {
        root(HttpsGithubComIronIronGitRefsHeadsMaster)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object MioBuild : BuildType({
    name = "Mio Build"

    vcs {
        root(HttpsGithubComCarllercheMioGitRefsHeadsMas)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object RocketBuild : BuildType({
    name = "Rocket Build"

    vcs {
        root(HttpsGithubComSergioBenitezRocketGitRefsHe)
    }

    steps {
        step {
            type = "cargo"
            param("cargo-command", "build")
        }
        step {
            type = "cargo"
            param("cargo-command", "test")
        }
    }

    triggers {
        vcs {
        }
    }
})

object HttpsGithubComAlexcrichtonFuturesRsGitRefs : GitVcsRoot({
    name = "https://github.com/alexcrichton/futures-rs.git#refs/heads/master"
    url = "https://github.com/alexcrichton/futures-rs.git"
})

object HttpsGithubComCarllercheMioGitRefsHeadsMas : GitVcsRoot({
    name = "https://github.com/carllerche/mio.git#refs/heads/master"
    url = "https://github.com/carllerche/mio.git"
})

object HttpsGithubComDieselRsDieselGitRefsHeadsMa : GitVcsRoot({
    name = "https://github.com/diesel-rs/diesel.git#refs/heads/master"
    url = "https://github.com/diesel-rs/diesel.git"
})

object HttpsGithubComGothamRsGothamGitRefsHeadsMa : GitVcsRoot({
    name = "https://github.com/gotham-rs/gotham.git#refs/heads/master"
    url = "https://github.com/gotham-rs/gotham.git"
})

object HttpsGithubComHyperiumHyperGitRefsHeadsMas : GitVcsRoot({
    name = "https://github.com/hyperium/hyper.git#refs/heads/master"
    url = "https://github.com/hyperium/hyper.git"
})

object HttpsGithubComIronIronGitRefsHeadsMaster : GitVcsRoot({
    name = "https://github.com/iron/iron.git#refs/heads/master"
    url = "https://github.com/iron/iron.git"
})

object HttpsGithubComSergioBenitezRocketGitRefsHe : GitVcsRoot({
    name = "https://github.com/SergioBenitez/Rocket.git#refs/heads/master"
    url = "https://github.com/SergioBenitez/Rocket.git"
})
