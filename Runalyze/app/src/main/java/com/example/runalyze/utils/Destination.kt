package com.example.runalyze.utils

import androidx.navigation.NavController
import androidx.navigation.navDeepLink

// Navigation destination
sealed class Destination(val route: String) {
    object CurrentRun : Destination("training") {
        val currentRunUriPattern = "https://runalyze.example.com/$route"
        val deepLinks = listOf(
            navDeepLink {
                uriPattern = currentRunUriPattern
            }
        )
    }
    object AddGoal: Destination("Goal"){

    }


    //navigation
    companion object {
        fun navigateToRunScreen(navController: NavController) {
            navController.navigate(CurrentRun.route)
        }
    }

}