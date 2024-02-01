plugins {
    id("logic.android.library")
    id("logic.android.library.jacoco")
}

android {
    namespace = "com.yyzy.main.core.common"
}

dependencies {
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
}