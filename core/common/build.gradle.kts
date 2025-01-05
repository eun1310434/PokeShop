plugins {
    alias(libs.plugins.euntaek.architecture.jvm.library)
    alias(libs.plugins.euntaek.architecture.hilt)
}

dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}