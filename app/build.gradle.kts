plugins {
    alias(libs.plugins.euntaek.architecture.android.application)
    alias(libs.plugins.euntaek.architecture.android.application.compose)
    alias(libs.plugins.euntaek.architecture.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.euntaek.pokeshop"
    defaultConfig {
        applicationId = "com.euntaek.pokeshop"
        versionCode = libs.versions.appVersionCode.get().toInt()
        versionName = libs.versions.appVersionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    implementation(projects.feature.home)

    implementation(projects.core.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.androidx.hilt.navigation.compose)
}