import com.android.build.gradle.LibraryExtension
import com.euntaek.pokeshop.LibraryID
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.findLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(id = PluginID.EUNTAEK_ARCHITECTURE_ANDROID_LIBRARY)
            applyPlugin(id = PluginID.EUNTAEK_ARCHITECTURE_HILT)
            applyPlugin(id = PluginID.KOTLIN_SERIALIZATION)

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(project(":core:data"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:navigation"))
                "implementation"(findLibrary(id = LibraryID.ANDROIDX_LIFECYCLE_RUN_TIME_COMPOSE))
                "implementation"(findLibrary(id = LibraryID.ANDROIDX_LIFECYCLE_VIEW_MODEL_COMPOSE))
            }
        }
    }
}
