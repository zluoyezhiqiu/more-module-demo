import com.build.logic.printlnLogic
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName:
 * @Description: CODE
 * @Date: 2024/2/1
 */
abstract class BasePlugin: Plugin<Project> {

    final override fun apply(target: Project) {
        applyPlugin(target)
        printlnLogic("gradle end !!!")
    }

    abstract fun applyPlugin(target: Project)
}