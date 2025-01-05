package com.euntaek.pokeshop

import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply


fun Project.applyPlugin(id: PluginID) {
    apply(plugin = id.value)
}

enum class PluginID(val value: String) {
    ANDROID_BASE("com.android.base"),
    ANDROID_APPLICATION("com.android.application"),
    ANDROID_LIBRARY("com.android.library"),
    ANDROID_TEST("com.android.test"),
    KOTLIN_COMPOSE("org.jetbrains.kotlin.plugin.compose"),
    KOTLIN_ANDROID("org.jetbrains.kotlin.android"),
    KOTLIN_SERIALIZATION("org.jetbrains.kotlin.plugin.serialization"),
    KOTLIN_JVM("org.jetbrains.kotlin.jvm"),
    DAGGER_HILT_ANDROID_PLUGIN("dagger.hilt.android.plugin"),
    KSP("com.google.devtools.ksp"),
    EUNTAEK_ARCHITECTURE_ANDROID_LIBRARY("euntaek.architecture.android.library"),
    EUNTAEK_ARCHITECTURE_HILT("euntaek.architecture.hilt"),
}