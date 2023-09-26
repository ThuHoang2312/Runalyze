package com.example.runalyze

import android.app.Application
import android.content.Context
import android.util.Log

class RunalyzeApp: Application() {
    private val tag = "Running Track App"

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(tag, "Running Track App onCreate()")
        appContext = applicationContext
    }
}