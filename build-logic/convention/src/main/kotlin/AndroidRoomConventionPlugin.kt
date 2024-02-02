import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.process.CommandLineArgumentProvider
import java.io.File

class AndroidRoomConventionPlugin : BasePlugin() {

    override fun applyPlugin(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")
            extensions.configure<KspExtension> {
                // The schemas directory contains a schema file for each version of the Room database.
                // This is required to enable Room auto migrations.
                // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
                arg(RoomSchemaArgProvider(File(projectDir, "schemas")))
            }
//            extensions.configure<LibraryExtension> {
//                buildTypes.configureEach {
//                    printlnLogic("findByName RoomSchemaArgProvider ---->>")
//                    javaCompileOptions.annotationProcessorOptions
//                        .compilerArgumentProvider(
//                            RoomSchemaArgProvider(
//                                File(projectDir, "schemas"),
//                                isKsp = false
//                            )
//                        )
//                }
//            }
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("room.runtime").get())
                add("implementation", libs.findLibrary("room.ktx").get())
                add("ksp", libs.findLibrary("room.compiler").get())
            }
        }
    }

    /**
     * https://issuetracker.google.com/issues/132245929
     * [Export schemas](https://developer.android.com/training/data-storage/room/migrating-db-versions#export-schemas)
     */
    class RoomSchemaArgProvider(
        @get:InputDirectory
        @get:PathSensitive(PathSensitivity.RELATIVE)
        val schemaDir: File,
        private val isKsp : Boolean = true
    ) : CommandLineArgumentProvider {
        override fun asArguments() = listOf(
            if (isKsp) "room.schemaLocation=${schemaDir.path}" else "-Aroom.schemaLocation=${schemaDir.path}",
            "room.incremental=true",
            "room.expandProjection=true"
        )
    }
}