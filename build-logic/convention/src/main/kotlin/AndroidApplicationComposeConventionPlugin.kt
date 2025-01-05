import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.euntaek.pokeshop.PluginID
import com.euntaek.pokeshop.applyPlugin
import com.euntaek.pokeshop.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        with(project) {
            applyPlugin(id = PluginID.ANDROID_APPLICATION)
            applyPlugin(id = PluginID.KOTLIN_COMPOSE)

            val extension = extensions.getByType<BaseAppModuleExtension>()
            configureAndroidCompose(commonExtension = extension)
        }
    }
}
