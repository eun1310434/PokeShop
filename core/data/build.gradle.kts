plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.euntaek.architecture.hilt)
}

android {
    namespace = "com.euntaek.pokeshop.core.data"
}

dependencies {
    api(projects.core.common)
    api(projects.core.database)
    api(projects.core.network)
}