package com.dev6.rejord

import android.app.Application
import com.dev6.core.util.DevicePrefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        prefs = DevicePrefs.getInstance(applicationContext)
    }
    companion object {
        lateinit var prefs: DevicePrefs
    }
}