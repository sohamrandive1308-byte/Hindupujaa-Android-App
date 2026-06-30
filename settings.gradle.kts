pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "hindupujaa"
include(":app")
include(":core:common")
include(":core:domain")
include(":core:data")
include(":core:ui")
include(":feature:auth")
include(":feature:home")
include(":feature:puja_detail")
include(":feature:kit_builder")
include(":feature:cart")
include(":feature:checkout")
include(":feature:orders")
include(":feature:store")
include(":feature:profile")
include(":feature:catering")
