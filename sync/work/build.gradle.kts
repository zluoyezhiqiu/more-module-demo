plugins {
    id("logic.android.library")
    id("logic.android.library.jacoco")
    id("logic.android.hilt")
}

android {
    namespace = "com.yyzy.sync.work"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.tracing.ktx)
    implementation(libs.androidx.work.ktx)

    implementation(libs.hilt.ext.work)
    implementation(libs.kotlinx.coroutines.android)

    kapt(libs.hilt.ext.compiler)
}
