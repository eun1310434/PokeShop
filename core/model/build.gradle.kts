plugins {
    alias(libs.plugins.euntaek.architecture.jvm.library)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    // kotlinx
    api(libs.kotlinx.immutable.collection)

    // serialize
    implementation(libs.kotlinx.serialization.json)
}