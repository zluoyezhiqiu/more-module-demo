plugins {
    id("logic.android.library")
    id("logic.android.library.jacoco")
}

android {
    viewBinding.isEnabled = true
    resourcePrefix = "feature_"
    namespace = "com.yyzy.main.feature.business"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    compileOnly(project(":core:database"))
}
