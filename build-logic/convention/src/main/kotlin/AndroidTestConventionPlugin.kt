import com.android.build.gradle.TestExtension
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.VersionID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.configureKotlinAndroid
import com.euntaek.pokeshop.findVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            applyPlugin(id = PluginID.ANDROID_TEST)
            applyPlugin(id = PluginID.KOTLIN_ANDROID)

            extensions.configure<TestExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = findVersion(id = VersionID.TARGET_SDK)
            }
        }
    }
}
