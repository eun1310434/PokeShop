package com.euntaek.pokeshop

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.provider.Provider

fun Project.findLibrary(id: LibraryID): Provider<MinimalExternalModuleDependency> =
    libs.findLibrary(id.value).get()


enum class LibraryID(val value: String) {
    ANDROIDX_HILT_NAVIGATION_COMPOSE("androidx.hilt.navigation.compose"),
    ANDROIDX_LIFECYCLE_RUN_TIME_COMPOSE("androidx.lifecycle.runtimeCompose"),
    ANDROIDX_LIFECYCLE_VIEW_MODEL_COMPOSE("androidx.lifecycle.viewModelCompose"),
    ANDROIDX_NAVIGATION_COMPOSE("androidx.navigation.compose"),
    ANDROIDX_UI_TOOLING("androidx.ui.tooling"),
    ANDROIDX_UI_TOOLING_PREVIEW("androidx.ui.tooling.preview"),
    KOTLINX_SERIALIZATION_JSON("kotlinx.serialization.json"),
    KOTLIN_TEST("kotlin.test"),
    HILT_COMPILER("hilt.compiler"),
    HILT_CORE("hilt.core"),
    HILT_ANDROID("hilt.android"),
    ANDROIDX_COMPOSE_BOM("androidx.compose.bom"),
    TIMBER("timber"),
}
