plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.github.ben-manes.versions")
    kotlin("android")
    kotlin("kapt")
}


android {
    namespace = "com.dev6.rejord"

    compileSdk = Versions.COMPILE_SDK_VERSION
    buildToolsVersion = Versions.BUILD_TOOLS_VERSION
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true

    defaultConfig {
        applicationId = "com.dev6.rejord"
        minSdk = Versions.MIN_SDK_VERSION
        targetSdk = Versions.TARGET_SDK_VERSION
        vectorDrawables.useSupportLibrary = true
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        /*
        getByName("release") {
        }

         */
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    //Android Core
    implementation(Kotlin.KOTLIN_STDLIB)
    implementation(Kotlin.COROUTINES_ANDROID)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Google.MATERIAL)
    implementation(Google.HILT_ANDROID)
    kapt(Google.HILT_ANDROID_COMPILER)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.ACTIVITY_KTX)
    implementation(AndroidX.FRAGMENT_KTX)
    implementation(AndroidX.LIFECYCLE_VIEWMODEL_KTX)
    implementation(AndroidX.LIFECYCLE_LIVEDATA_KTX)
    implementation(AndroidX.LIFECYCLE_EXTENSIONNS)

    //retrofit2
    implementation(Network.OKHTTP)
    implementation(Network.OKHTTP3)
    implementation(Network.RETROFIT)
    implementation(Network.GSON)
    implementation(Network.SCALAR)


    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.5.3")
    implementation(project(":feature:login"))
    implementation(project(":feature:join"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core"))
    implementation(project(":common"))



}