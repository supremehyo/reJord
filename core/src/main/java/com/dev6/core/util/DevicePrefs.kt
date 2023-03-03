package com.dev6.core.util

import android.content.Context

class DevicePrefs(context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private var edit = prefs.edit()

    fun saveToken(access : String , refresh : String){
        edit.putString(ACCESSTOKEN , access).apply()
        edit.putString(REFRESHTOKEN, refresh).apply()
    }

    fun getToken() = prefs.getString(ACCESSTOKEN , "") ?: ""
    fun getRefreshToken() = prefs.getString(REFRESHTOKEN , "") ?: ""
    companion object {
        private var instance: DevicePrefs ?= null

        fun getInstance(context: Context) = instance ?: synchronized(this) {
            DevicePrefs(context).apply {
                instance = this
            }
        }

        const val PREF_NAME = "REJORD DATA"
        const val ACCESSTOKEN = "ACCESSTOKEN"
        const val REFRESHTOKEN = "REFRESHTOKEN"
    }
}