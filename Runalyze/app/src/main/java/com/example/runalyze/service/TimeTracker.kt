package com.example.runalyze.service

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class TimeTracker {

    private var coroutineScope = CoroutineScope(Dispatchers.Main)

    private var timeElapsedInMillis = 0L
    private var isRunning = false
    private var callback: ((timeInMillis: Long) -> Unit)? = null
    private var job: Job? = null

    private fun start() {
        if (job != null)
            return
        System.currentTimeMillis()
        this.job = coroutineScope.launch {
            while (isRunning && isActive) {
                callback?.invoke(timeElapsedInMillis)
                delay(1000)
                timeElapsedInMillis += 1000
            }
        }
    }

    fun startResumeTimer(callback: (timeInMillis: Long) -> Unit) {
        if (isRunning)
            return
        this.callback = callback
        isRunning = true
        start()
    }

    fun stopTimer() {
        pauseTimer()
        timeElapsedInMillis = 0
    }

    fun pauseTimer() {
        isRunning = false
        job?.cancel()
        job = null
        callback = null
    }

}