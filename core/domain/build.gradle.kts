plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.euntaek.architecture.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.euntaek.pokeshop.core.domain"
}

dependencies {
    api(projects.core.data)
    api(projects.core.model)
}