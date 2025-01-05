import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.VersionID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.configureKotlinAndroid
import com.euntaek.pokeshop.findVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(id = PluginID.ANDROID_APPLICATION)
            applyPlugin(id = PluginID.KOTLIN_ANDROID)

            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = findVersion(id = VersionID.TARGET_SDK)
                testOptions.animationsDisabled = true
            }
        }
    }
}