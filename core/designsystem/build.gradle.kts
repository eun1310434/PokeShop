plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.euntaek.architecture.android.library.compose)
}

android {
    namespace = "com.euntaek.pokeshop.core.designsystem"
}

dependencies {
    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.compose.animation)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.material3)

    // Image background
    api(libs.androidx.palette.ktx)

    // Image loading
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.kt.gif)
}