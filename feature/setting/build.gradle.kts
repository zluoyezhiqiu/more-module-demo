plugins {
    id("logic.android.library")
    id("logic.android.library.jacoco")
    id("logic.android.hilt")
}

android {
    viewBinding.isEnabled = true
    resourcePrefix = "set_"
    namespace = "com.yyzy.feature.setting"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.navigation.fragment)
}