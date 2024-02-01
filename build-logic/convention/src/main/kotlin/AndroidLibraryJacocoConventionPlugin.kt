import com.android.build.api.variant.LibraryAndroidComponentsExtension
import com.build.logic.configureJacoco
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

/**
 * 这样配置后，在运行测试任务后，Jacoco 插件会生成相应的代码覆盖率报告，
 * 你可以在项目的 build/reports/jacoco/test/html/index.html 文件中查看可视化的覆盖率报告。
 */
class AndroidLibraryJacocoConventionPlugin : BasePlugin() {

    override fun applyPlugin(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.gradle.jacoco")
                apply("com.android.library")
            }
            val extension = extensions.getByType<LibraryAndroidComponentsExtension>()
            configureJacoco(extension)
        }
    }
}