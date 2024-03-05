import com.yyzy.logic.loge
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.KaptExtension

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName:
 * @Description: CODE
 * @Date: 2024/3/4
 */
class ArouterConventionPlugin : BasePlugin() {
    override fun applyPlugin(target: Project) {
        with(target) {
            with(pluginManager) {
                if (findPlugin("org.jetbrains.kotlin.kapt")?.namespace == null){
                    apply("org.jetbrains.kotlin.kapt")
                    target.loge("ArouterConventionPlugin","apply jetbrains.kotlin.kapt name:$name")
                }
            }
            extensions.configure<KaptExtension> {
                arguments {
                    arg("AROUTER_MODULE_NAME", name)
                }
            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("arouter.api").get())
                "kapt"(libs.findLibrary("arouter.compiler").get())
            }
        }
    }
}