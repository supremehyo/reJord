package com.dev6.rejord

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Hilt Android App 초기화
@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}