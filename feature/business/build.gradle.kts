import com.yyzy.logic.CoreModule
import com.yyzy.logic.runtimeOnlyProject

plugins {
    id("logic.android.auto.runner")
    id("logic.android.hilt")
}

android {
    viewBinding.isEnabled = true
    resourcePrefix = "bus_"
    namespace = "com.yyzy.feature.business"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    compileOnly(project(":core:database"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx)

    implementation(libs.androidx.navigation.fragment)

    if (project.isRunAsApp) {
        runtimeOnlyProject(CoreModule.Network)
    }
}
