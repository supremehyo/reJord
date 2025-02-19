import Versions.KOTLINX_COROUTINES

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

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-inline:2.21.0")
    androidTestImplementation("org.mockito:mockito-android:2.24.5")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.annotation:annotation:1.5.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:$KOTLINX_COROUTINES")

    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":feature:home"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(Kotlin.KOTLIN_STDLIB)
    implementation(AndroidX.ACTIVITY_KTX)
    implementation(AndroidX.FRAGMENT_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(AndroidX.LIFECYCLE_EXTENSIONNS)
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Kotlin.COROUTINES_TEST)
    implementation(AndroidX.APP_COMPAT)

    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
}