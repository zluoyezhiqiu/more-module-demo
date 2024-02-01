import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.yyzy.logic.configureJacoco
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationJacocoConventionPlugin : BasePlugin() {
    override fun applyPlugin(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.gradle.jacoco")
                apply("com.android.application")
            }
            val extension = extensions.getByType<ApplicationAndroidComponentsExtension>()
            configureJacoco(extension)
        }
    }

}