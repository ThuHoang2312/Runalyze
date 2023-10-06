package com.example.runalyze.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.Run
import com.example.runalyze.service.location.TrackingManager
import kotlinx.coroutines.launch
import java.math.RoundingMode
import java.util.Date

class RunViewModel(
    private val trackingManager: TrackingManager,
    private val roomDb: AppDb,
    ): ViewModel() {

    private val tag = "RunAlyze"
    val currentRunState = trackingManager.currentRunState
    val runningDurationInMillis = trackingManager.trackingDurationInMs

    fun playPauseTracking(){
        if(currentRunState.value.isTracking){
            trackingManager.pauseTracking()
        }else {
            trackingManager.startResumeTracking()
        }
    }

    fun finishRun(){
        trackingManager.pauseTracking()
        saveRun(
            Run(
                avgSpeedInKMH = currentRunState.value.distanceInMeters
                    .toBigDecimal()
                    .multiply(3600.toBigDecimal())
                    .divide(runningDurationInMillis.value.toBigDecimal(), 2, RoundingMode.HALF_UP)
                    .toFloat(),
                distanceInMeters = currentRunState.value.distanceInMeters,
                durationInMillis = runningDurationInMillis.value,
                timestamp = Date().time,
            )
        )
        trackingManager.stop()
    }

    fun saveRun(run: Run) {
        viewModelScope.launch {
            Log.d(tag, "Save run ${run}")
            roomDb.runDao.addRun(run)
        }
    }

}