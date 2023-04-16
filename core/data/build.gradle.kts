plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}
android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Google.HILT_ANDROID)
    implementation(project(mapOf("path" to ":core")))
    kapt(Google.HILT_ANDROID_COMPILER)
    //retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.GSON)
    implementation(Network.SCALAR)
    implementation(Network.MOCKWEBSERVER)
    implementation(Debug.TIMBER)
    testImplementation(Kotlin.COROUTINES_TEST)
    testImplementation(UnitTest.JUNIT)
    testImplementation(UnitTest.TRUTH)
    testImplementation(AndroidX.CORE_TESTING)
    testImplementation(Google.HILT_TESTING)
    androidTestImplementation(Kotlin.COROUTINES_TEST)

    implementation("androidx.core:core-ktx:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("androidx.test:core-ktx1.4.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.annotation:annotation:1.5.0")
    implementation(project(":core:domain"))

}