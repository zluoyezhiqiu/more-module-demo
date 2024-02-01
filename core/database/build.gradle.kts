plugins {
    id("logic.android.library")
    id("logic.android.library.jacoco")
    id("logic.android.room")
    id("logic.android.hilt")
}

android {
    namespace = "com.yyzy.main.core.database"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.datetime)
    implementation(project(":core:common"))
}
