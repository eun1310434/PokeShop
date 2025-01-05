plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.euntaek.architecture.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.euntaek.pokeshop.core.network"
    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    api(projects.core.common)
    api(projects.core.model)

    // network
    api(platform(libs.retrofit.bom))
    api(libs.bundles.retrofitBundle)
    implementation(platform(libs.okhttp.bom))
    testImplementation(libs.okhttp.mockwebserver)

    // json parsing
    implementation(libs.kotlinx.serialization.json)
}