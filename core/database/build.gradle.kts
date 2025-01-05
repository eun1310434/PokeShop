plugins {
    alias(libs.plugins.euntaek.architecture.android.library)
    alias(libs.plugins.euntaek.architecture.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.euntaek.pokeshop.core.database"

    defaultConfig {
        // The schemas directory contains a schema file for each version of the Room database.
        // This is required to enable Room auto migrations.
        // See https://developer.android.com/reference/kotlin/androidx/room/AutoMigration.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }

    sourceSets.getByName("test") {
        assets.srcDir(files("$projectDir/schemas"))
    }
}

dependencies {
    api(projects.core.model)

    // Paging
    api(libs.androidx.paging.runtime.ktx)
    api(libs.androidx.paging.compose)
    api(libs.androidx.room.paging)

    // coroutines
    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.kotlinx.coroutines.test)

    // database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.arch.core.testing)

    // unit test
    testImplementation(libs.junit)
    testImplementation(libs.androidx.test.core)
    testImplementation(libs.robolectric)
}