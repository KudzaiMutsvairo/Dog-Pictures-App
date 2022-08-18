package com.kudzai.mutswairo.dogpics

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DPApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}