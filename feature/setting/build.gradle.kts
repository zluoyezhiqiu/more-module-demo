plugins {
    id("logic.android.feature")
    id("logic.android.library.jacoco")
}

android {
    viewBinding.isEnabled = true
    resourcePrefix = "set_"
    namespace = "com.yyzy.feature.setting"
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.navigation.fragment)
}