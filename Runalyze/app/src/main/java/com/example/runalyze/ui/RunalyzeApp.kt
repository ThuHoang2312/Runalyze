package com.example.runalyze.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.ui.screen.Home
import com.example.runalyze.ui.screen.TrainingScreen

@Composable
fun RunalyzeApp(){
    Navigation()
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            Home(navController = navController)
        }
        composable("Training"){
            TrainingScreen(navController = navController)
        }
    }
}