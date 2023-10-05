package com.example.runalyze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.runalyze.database.AppDb
import com.example.runalyze.service.location.LocationData

class RunViewModel(application: Application): AndroidViewModel(application) {

    private val roomDB = AppDb.getInstance(application)
    private val locationData = LocationData(application)

    fun getLocationData() = locationData
    fun startLocationUpdates(){
        locationData.startLocationUpdates()
    }
}