pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( "https://jitpack.io" )
    }
}

rootProject.name = "rejord"
include (":app")
include(":core")
include(":core:data")
include(":core:designsystem")
include(":core:domain")
include(":common")
include(":feature")
include(":feature:join")
include(":feature:login")
