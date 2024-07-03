plugins {
    id("logic.android.library")
    id("logic.android.hilt")
}

android {
    namespace = "com.yyzy.main.core.testing"
}

dependencies {

    api(libs.junit4)
    api(libs.turbine)
    api(libs.hilt.android.testing)

    api(libs.androidx.test.core)
    api(libs.androidx.test.espresso.core)
    api(libs.androidx.test.espresso.contrib)
    
    api(libs.androidx.test.rules)
    api(libs.androidx.test.runner)
    api(libs.androidx.test.ext)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.test.uiautomator)

    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:database"))
    implementation(libs.kotlinx.datetime)
}
