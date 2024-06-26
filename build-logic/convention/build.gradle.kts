@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.yyzy.main.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradle)
    compileOnly(libs.firebase.performance.gradle)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)

    compileOnly(libs.common)
    compileOnly(libs.manifest.merger)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "logic.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidFlavors") {
            id = "logic.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }
        register("androidApplicationJacoco") {
            id = "logic.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoConventionPlugin"
        }

        register("androidLibrary") {
            id = "logic.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryJacoco") {
            id = "logic.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoConventionPlugin"
        }

        register("androidHilt") {
            id = "logic.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidRoom") {
            id = "logic.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("autoRunnerPlugin") {
            id = "logic.android.auto.runner"
            implementationClass = "AndroidAutoRunnerPlugin"
        }

        register("androidFeature") {
            id = "logic.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidTest") {
            id = "logic.android.test"
            implementationClass = "AndroidTestConventionPlugin"
        }
    }
}