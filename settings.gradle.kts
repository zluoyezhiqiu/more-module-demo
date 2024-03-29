pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "more-module-demo"
include (":app")
include (":core:database")
include(":core:common")
include(":core:data")
include(":core:model")
include(":core:network")
include(":feature:business")
include(":sync:work")
include(":feature:setting")
