package com.euntaek.pokeshop

import org.gradle.api.JavaVersion
import org.gradle.api.Project


val Project.javaVersion: JavaVersion
    get(): JavaVersion = JavaVersion.toVersion(findVersion(id = VersionID.JVM_VERSION))

fun Project.findVersion(id: VersionID): Int {
    return libs.findVersion(id.value).get().toString().toInt()
}

enum class VersionID(val value: String) {
    TARGET_SDK("targetSdk"),
    JVM_VERSION("jvmVersion"),
    COMPILE_SDK("compileSdk"),
    MIN_SDK("minSdk"),
}
