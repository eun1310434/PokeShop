plugins {
    alias(libs.plugins.euntaek.architecture.android.feature)
    alias(libs.plugins.euntaek.architecture.android.library.compose)
}

android {
    namespace = "com.euntaek.pokeshop.feature.home"
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    testImplementation(libs.robolectric)

    testImplementation(projects.core.testing)
}