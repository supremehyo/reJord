plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    //id("dagger.hilt.android.plugin")
    kotlin("kapt")
}
android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation(project(":common"))
    //Android Core
    implementation(Kotlin.KOTLIN_STDLIB)
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.ACTIVITY_KTX)
    implementation(AndroidX.FRAGMENT_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(AndroidX.LIFECYCLE_EXTENSIONNS)
    implementation(AndroidX.APP_COMPAT)
    implementation(Google.MATERIAL)
    implementation(Debug.TIMBER)
}