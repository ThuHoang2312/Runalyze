package com.example.runalyze.utils

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.content.ContextCompat

object RunUtils {
    val locationPermissions = listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    ).toTypedArray()

    val notificationPermission = Manifest.permission.POST_NOTIFICATIONS

    val allPermissions = mutableListOf<String>().apply {
        addAll(locationPermissions)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            add(notificationPermission)
        }
    }.toTypedArray()

    fun Context.hasNotificationPermission() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ContextCompat.checkSelfPermission(
                this,
                notificationPermission
            ) == PackageManager.PERMISSION_GRANTED
        } else true


    fun Context.hasLocationPermission() =
        locationPermissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

    fun Context.hasAllPermission() =
        allPermissions.all {
            ContextCompat.checkSelfPermission(
                this,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }

    fun Context.openAppSetting() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", packageName, null)
        ).also(::startActivity)
    }

}