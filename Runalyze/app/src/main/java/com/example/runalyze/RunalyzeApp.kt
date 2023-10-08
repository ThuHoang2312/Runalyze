package com.example.runalyze

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.runalyze.service.notification.NotificationHelper

class RunalyzeApp : Application() {
    private val tag = "Running Track App"

    companion object {
        lateinit var appContext: Context
        lateinit var appModule: AppModule
        lateinit var notificationHelper: NotificationHelper
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImplement(this)
        notificationHelper = appModule.notificationHelper
        notificationHelper.createNotificationChannel()
        Log.d(tag, "Running Track App onCreate()")
        appContext = applicationContext
    }
}