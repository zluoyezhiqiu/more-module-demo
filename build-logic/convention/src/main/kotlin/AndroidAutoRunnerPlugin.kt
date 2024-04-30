import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import com.android.manifmerger.ManifestMerger2
import com.android.manifmerger.MergingReport
import com.android.utils.StdLogger
import com.yyzy.logic.loge
import org.apache.tools.ant.BuildException
import org.gradle.api.Project
import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logging
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.extra
import java.io.FileWriter
import java.util.Locale
import java.util.regex.Pattern

/**
 * @Author: ljl
 * @Email: ljl@dofun.cc
 * @ClassName:
 * @Description: CODE
 * @Date: 2024/2/2
 */
class AndroidAutoRunnerPlugin : BasePlugin() {

    private var taskIsAssemble: Boolean = false
    private var mainModuleName: String? = null

    private val logger = Logging.getLogger(this::class.java)

    override fun applyPlugin(target: Project) {
        initByTask(target)
        val runAsApp = isRunAsApp(target)
        if (runAsApp) {
            target.apply("plugin" to "logic.android.application")
            target.apply("plugin" to "logic.android.application.flavors")
            target.apply("plugin" to "logic.android.application.jacoco")
        } else {
            target.apply("plugin" to "logic.android.library")
            target.apply("plugin" to "logic.android.library.jacoco")
        }
        target.apply("plugin" to "org.jetbrains.kotlin.android")
        target.extra.set("runAsApp", runAsApp)
        manifestMerger(runAsApp, target)
    }

    private fun manifestMerger(runAsApp: Boolean, project: Project) {
        if (runAsApp) {
            val appExtension = project.extensions.findByType(ApplicationExtension::class.java)!!
            appExtension.sourceSets.findByName("main")?.run {
                //debug模式下，如果存在src/main/debug/AndroidManifest.xml，则使用它和组件本身的manifest文件进行合并
                val debugManifest = "${DEBUG_DIR}AndroidManifest.xml"
                val debugManifestFile = project.file(debugManifest)
                if (debugManifestFile.exists()) {
                    val oldManifestSrcFile = project.file(manifest.toString())
                    val iLogger = StdLogger(StdLogger.Level.VERBOSE)
                    val manifestMerger = ManifestMerger2.newMerger(
                        debugManifestFile, iLogger, ManifestMerger2.MergeType.APPLICATION
                    )
                    // MergeType为APPLICATION时会进行占位符替换操作，当前合并并不需要替换
                    manifestMerger.withFeatures(ManifestMerger2.Invoker.Feature.NO_PLACEHOLDER_REPLACEMENT)
                    manifestMerger.addLibraryManifests(oldManifestSrcFile)
                    val outputFile = project.file("${project.buildDir}/debug/AndroidManifest.xml")
                    manifestMerger.setMergeReportFile(outputFile)
                    val mergingReport = manifestMerger.merge()
                    if (mergingReport.result.isError) {
                        logger.error(mergingReport.reportString)
                        mergingReport.log(iLogger)
                        throw BuildException(mergingReport.reportString)
                    }
                    FileWriter(outputFile).use { fileWriter ->
                        fileWriter.append(
                            mergingReport
                                .getMergedDocument(MergingReport.MergedManifestKind.MERGED)
                        )
                    }
                    manifest.srcFile(outputFile)
                }

                //debug模式下，如果存在src/main/debug/assets，则自动将其添加到assets源码目录
                if (project.file("${DEBUG_DIR}assets").exists()) {
                    assets.srcDir("${DEBUG_DIR}assets")
                }
                //debug模式下，如果存在src/main/debug/java，则自动将其添加到java源码目录
                if (project.file("${DEBUG_DIR}java").exists()) {
                    java.srcDir("${DEBUG_DIR}java")
                }
                //debug模式下，如果存在src/main/debug/res，则自动将其添加到资源目录
                if (project.file("${DEBUG_DIR}res").exists()) {
                    res.srcDir("${DEBUG_DIR}res")
                }
            }
        }
    }

    private fun initByTask(project: Project) {
        val allModuleBuildApkPattern = Pattern.compile(TASK_TYPES)
        project.gradle.startParameter.taskNames.forEach { task ->
            loge(project, "task===>$task")
            if (allModuleBuildApkPattern.matcher(task.uppercase(Locale.getDefault())).matches()) {
                taskIsAssemble = true
                if (task.contains(":")) {
                    val arr = task.split(":")
                    mainModuleName = arr[arr.size - 2].trim()
                }
                if (mainModuleName.isNullOrEmpty()) {
                    project.rootProject.allprojects.forEach projects@{ p ->
                        try {
                            if (p.extra["mainApp"] as Boolean) {
                                mainModuleName = p.name
                                loge(project, "mainModuleName:${mainModuleName}")
                                return@projects
                            }
                        } catch (_: Exception) {
                        }
                    }
                }
                return@forEach
            }
        }
    }

    private fun loge(project: Project, msg: String) {
        project.loge(TAG, msg)
    }

    private fun isRunAsApp(project: Project): Boolean {
        val appOrLib = project.extensions.findByType(LibraryExtension::class.java)
            ?: project.extensions.findByType(ApplicationExtension::class.java)
        val mainApp = isMainApp(project) || appOrLib is ApplicationExtension
        val assembleFor = isAssembleFor(project)
        val alwaysLib = isAlwaysLib(project) || appOrLib is LibraryExtension
        val runAsApp = when {
            mainApp -> true
            alwaysLib -> false
            else -> assembleFor || !taskIsAssemble
        }
        logger.log(
            LogLevel.ERROR,
            "${project.name} isRunAsApp:$runAsApp, mainApp=$mainApp, mainModuleName=$mainModuleName, " +
                    "taskIsAssemble=$taskIsAssemble, assembleFor=$assembleFor, alwaysLib=${
                        project.extra.has("alwaysLib")
                    }"
        )
        return runAsApp
    }

    private fun isMainApp(project: Project) =
        project.extra.has("mainApp") && project.extra["mainApp"] as? Boolean == true

    private fun isAssembleFor(project: Project) = project.name == mainModuleName

    private fun isAlwaysLib(project: Project): Boolean {
        return project.extra.has("alwaysLib") && project.extra["alwaysLib"] as? Boolean == true
    }
}

private const val TASK_TYPES =
    ".*((((ASSEMBLE)|(BUILD)|(INSTALL)|((BUILD)?TINKER)|(RESGUARD)).*)|(ASR)|(ASD))"
private const val DEBUG_DIR = "src/main/debug/"
private const val TAG = ">> AutoRunner"

val Project.isRunAsApp: Boolean
    get() {
        return extra["runAsApp"] as? Boolean == true
    }