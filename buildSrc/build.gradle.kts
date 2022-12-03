plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    maven("https://jitpack.io")
    gradlePluginPortal()
}

object PluginVersion {
    const val KOTLIN = "1.7.10"
    const val GRADLE_PLUGIN = "0.41.0"
    const val NAVIGATION = "2.5.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:7.3.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginVersion.KOTLIN}")
    implementation("com.github.ben-manes:gradle-versions-plugin:${PluginVersion.GRADLE_PLUGIN}")
    implementation("com.google.gms:google-services:4.3.14")
    implementation("com.google.firebase:firebase-crashlytics-gradle:2.8.1")
    implementation("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginVersion.NAVIGATION}")

}