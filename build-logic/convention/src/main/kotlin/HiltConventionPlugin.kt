import com.android.build.gradle.api.AndroidBasePlugin
import com.euntaek.pokeshop.LibraryID
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(id = PluginID.KSP)
            dependencies {
                "ksp"(findLibrary(id = LibraryID.HILT_COMPILER))
            }

            // Add support for Jvm Module, base on org.jetbrains.kotlin.jvm
            pluginManager.withPlugin(PluginID.KOTLIN_JVM.value) {
                dependencies {
                    "implementation"(findLibrary(id = LibraryID.HILT_CORE))
                }
            }

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            pluginManager.withPlugin(PluginID.ANDROID_BASE.value) {
                applyPlugin(id = PluginID.DAGGER_HILT_ANDROID_PLUGIN)
                dependencies {
                    "implementation"(findLibrary(id = LibraryID.HILT_ANDROID))
                }
            }
        }
    }
}
