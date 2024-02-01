import com.android.build.api.dsl.ApplicationExtension
import com.yyzy.logic.configureFlavors
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * 用于导航多模块之间的路径
 */
class AndroidApplicationFlavorsConventionPlugin : BasePlugin() {
    override fun applyPlugin(target: Project) {
        with(target) {
            extensions.configure<ApplicationExtension> {
                configureFlavors(this)
            }
        }
    }
}