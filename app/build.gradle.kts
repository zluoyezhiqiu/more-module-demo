plugins {
    id("logic.android.application")
    id("logic.android.hilt")
    id("logic.android.application.flavors")
    id("logic.android.application.jacoco")
    id("jacoco")
}

android {
    defaultConfig {
        applicationId = "com.yyzy.main"
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    namespace = "com.yyzy.main"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":feature:business"))
    implementation(project(":feature:setting"))
    implementation(project(":sync:work"))
    compileOnly(project(":core:database"))

    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core.v340)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamic.feature)
}