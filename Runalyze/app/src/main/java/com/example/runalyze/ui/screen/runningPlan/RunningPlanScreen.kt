package com.example.runalyze.ui.screen.runningPlan

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.viewmodel.RunningPlanViewModel

@Composable
fun RunningPlanScreen() {
    val navController = rememberNavController()
    val runningPlanViewModel = RunningPlanViewModel()
    runningPlanViewModel.getRunningPlanList()
    val runningPlanList: List<RunningPlan> by runningPlanViewModel.runningPlanList.observeAsState(mutableListOf())
    NavHost(navController = navController, startDestination = "runningPlanList") {
        composable("runningPlanList") {
            RunningPlanListScreen(runningPlanList, navController = navController)
        }
        composable("runningPlanDetail/{runningPlanId}") {
                backStackEntry ->
            RunningPlanDetailView(backStackEntry.arguments?.getInt("runningPlanId") ?: 1, runningPlanList)
//            Log.d("aaaa nav id", (it.arguments?.getInt("runningPlanId")).toString())
//            val planId = it.arguments?.getInt("runningPlanId") ?: 1
//            RunningPlanDetailView(planId = planId, runningPlanList)

        }
    }
}