object Kotlin {
    const val KOTLIN_STDLIB = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN_VERSION}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLINX_COROUTINES}"
    const val COROUTINES_TEST =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.KOTLINX_COROUTINES}"
}

object AndroidX {
    const val CORE_KTX = "androidx.core:core-ktx:${Versions.CORE_KTX}"
    const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"

    const val ACTIVITY_KTX = "androidx.activity:activity-ktx:${Versions.ACTIVITY_KTX}"
    const val FRAGMENT_KTX = "androidx.fragment:fragment-ktx:${Versions.FRAGMENT_KTX}"

    const val LIFECYCLE_VIEWMODEL_KTX =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_LIVEDATA_KTX =
        "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE_KTX}"
    const val LIFECYCLE_EXTENSIONNS = "androidx.lifecycle:lifecycle-extensions:2.1.0-alpha01"

    const val NAVIGATION = "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
    const val NAVIGATION_KTX = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

    const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
    const val PAGING_DOMAIN = "androidx.paging:paging-common:${Versions.PAGING}"

    const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"

    const val WORK_MANAGER = "androidx.work:work-runtime-ktx:${Versions.WORK_VERSION}"
    const val WORK_TEST = "androidx.work:work-testing:${Versions.WORK_VERSION}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
}

object Google {
    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_ANDROID_COMPILER = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
    const val HILT_TESTING = "com.google.dagger:hilt-android-testing:${Versions.HILT}"
    const val HILT_WORKER = "androidx.hilt:hilt-work:1.0.0"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"

    // CRASHLYTICS
    const val GOOGLE_CORE = "com.google.android.play:core-ktx:1.8.1"
    const val FIREBASE_BOM = "com.google.firebase:firebase-bom:29.2.1"
    const val CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val ANALYTICS = "com.google.firebase:firebase-crashlytics-ktx"
    const val AUTH = "com.google.android.gms:play-services-auth:20.1.0"
}

object Image {
    const val GLIDE = "com.github.bumptech.glide:glide:4.13.2"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:4.13.2"
    const val TED_IMAGE_PICKER = "io.github.ParkSangGwon:tedimagepicker:1.2.8"
    const val FIREBASE_STORAGE = "com.google.firebase:firebase-storage"
}

object UnitTest {
    const val JUNIT = "junit:junit:${Versions.JUNIT}"
    const val TRUTH = "com.google.truth:truth:1.1.3"
    const val MOCKITO = "org.mockito:mockito-inline:4.6.1"
    const val TURBINE = "app.cash.turbine:turbine:0.8.0"
}

object AndroidTest {
    const val ANDROID_JUNIT = "androidx.test.ext:junit:${Versions.ANDROID_JUNIT}"
    const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:${Versions.ESPRESSO_CORE}"
    const val ESPRESSO_CONTRIB = "androidx.test.espresso:espresso-contrib:${Versions.ESPRESSO_CORE}"
    const val ANDROID_RUNNER = "androidx.test:runner:1.1.0"
}

// 네트워크 관련
object Network {
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val GSON = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val SCALAR = "com.squareup.retrofit2:converter-scalars:2.9.0"
    const val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    const val OKHTTP3 = "com.squareup.okhttp3:logging-interceptor:4.9.1"
    const val MOCKWEBSERVER = "com.squareup.okhttp3:mockwebserver:4.10.0"
}

// Flipper 디버깅 편리하게 해주는 라이브러리
object Debug {
    const val TIMBER = "com.jakewharton.timber:timber:5.0.1"
    const val FLIPPER = "com.facebook.flipper:flipper:0.135.0"
    const val SOLOADER = "com.facebook.soloader:soloader:0.10.1"
    const val LEAK_FLIPPER = "com.facebook.flipper:flipper-leakcanary2-plugin:0.135.0"
}

// 메모리 누수 잡는 라이브러리
object Memory {
    const val LEAK_CANARY = "com.squareup.leakcanary:leakcanary-android:2.9.1"
}

object Kakao {
    const val LOGIN = "com.kakao.sdk:v2-user:2.8.6"
}

object RecyclerView {
    const val Groupie = "com.github.lisawray.groupie:groupie:2.10.1"
    const val GroupieViewBinding = "com.github.lisawray.groupie:groupie-viewbinding:2.10.1"
}

object Indicator {
    const val CIRCLE_INDICATOR = "com.tbuonomo:dotsindicator:4.3"
}

object Room {
    const val ROOM_COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val ROOM = "androidx.room:room-ktx:${Versions.ROOM}"
    const val ROOM_TESTING = "androidx.room:room-testing:${Versions.ROOM}"
}
