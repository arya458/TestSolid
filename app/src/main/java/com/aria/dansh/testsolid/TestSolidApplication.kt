package com.aria.dansh.testsolid

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
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