package com.aria.dansh.testsolid

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestSolidApplication  :Application(){

    override fun onCreate() {
        super.onCreate()
    }

    companion object {
        const val CHANNEL_ID = "reminder_id"
    }

}