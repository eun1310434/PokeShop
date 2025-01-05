import com.euntaek.pokeshop.LibraryID
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.configureKotlinJvm
import com.euntaek.pokeshop.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(id = PluginID.KOTLIN_JVM)
            configureKotlinJvm()
            dependencies {
                "testImplementation"(findLibrary(id = LibraryID.KOTLIN_TEST))
            }
        }
    }
}
