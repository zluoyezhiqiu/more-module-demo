plugins {
    id("logic.android.library")
    id("logic.android.hilt")
    id("logic.android.library.jacoco")
    id("kotlinx-serialization")
}

android {
    namespace = "com.yyzy.data"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))

    implementation(libs.androidx.navigation.ui)
}
