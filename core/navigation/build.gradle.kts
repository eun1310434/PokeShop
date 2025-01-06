plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.euntaek.pokeshop.core.navigation"
}

dependencies {
    api(libs.androidx.hilt.navigation.compose)
    api(libs.androidx.navigation.compose)

    // serialize
    implementation(libs.kotlinx.serialization.json)
}