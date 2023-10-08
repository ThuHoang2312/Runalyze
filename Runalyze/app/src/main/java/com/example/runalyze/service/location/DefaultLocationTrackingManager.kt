package com.example.runalyze.service.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import com.example.runalyze.utils.RunUtils.hasLocationPermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest


interface LocationTrackingManager {
    fun registerCallback(locationCallback: LocationCallback)

    fun unRegisterCallback(locationCallback: LocationCallback)
}

@SuppressLint("MissingPermission")
class DefaultLocationTrackingManager(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val context: Context,
    private val locationRequest: LocationRequest
    ): LocationTrackingManager {

    override fun registerCallback(locationCallback: LocationCallback) {
        if (context.hasLocationPermission()) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    override fun unRegisterCallback(locationCallback: LocationCallback) {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }
}