import com.android.build.gradle.LibraryExtension
import com.euntaek.pokeshop.LibraryID
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.VersionID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.configureKotlinAndroid
import com.euntaek.pokeshop.findLibrary
import com.euntaek.pokeshop.findVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(id = PluginID.ANDROID_LIBRARY)
            applyPlugin(id = PluginID.KOTLIN_ANDROID)

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(commonExtension = this)
                defaultConfig.targetSdk = findVersion(id = VersionID.TARGET_SDK)
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                testOptions.animationsDisabled = true
            }

            dependencies {
                "implementation"(findLibrary(id = LibraryID.TIMBER))
                "androidTestImplementation"(findLibrary(id = LibraryID.KOTLIN_TEST))
                "testImplementation"(findLibrary(id = LibraryID.KOTLIN_TEST))
            }
        }
    }
}
