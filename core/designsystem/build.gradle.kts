plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}


dependencies {
    implementation(Google.MATERIAL)
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("androidx.core:core-ktx:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0")
    androidTestImplementation("androidx.annotation:annotation:1.5.0")
    implementation ("androidx.core:core-splashscreen:1.0.0-rc01")
}