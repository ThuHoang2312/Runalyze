package com.example.runalyze

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.DirectionsRun
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String){
    object Home: BottomNavItem("home", icon = Icons.Outlined.Home, title = "Home")
    object Training: BottomNavItem("training", icon = Icons.AutoMirrored.Outlined.DirectionsRun, title = "Training")
    object Activity: BottomNavItem(route = "activity", icon = Icons.Outlined.QueryStats, title = "Activity")
    object Profile: BottomNavItem(route = "profile", icon = Icons.Outlined.Person, title = "Profile")
}
