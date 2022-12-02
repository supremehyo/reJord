package com.dev6.rejord.di
import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.Window
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun providesWindow(activity: Activity): Window {
        return activity.window
    }
}