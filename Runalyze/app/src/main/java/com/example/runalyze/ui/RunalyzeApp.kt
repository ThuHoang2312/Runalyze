package com.example.runalyze.ui

import RunScreen
import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.BottomNavItem
import com.example.runalyze.components.BottomNavigationMenu
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.service.location.LocationDetails
import com.example.runalyze.ui.screen.Activity
import com.example.runalyze.ui.screen.AddGoalView
import com.example.runalyze.ui.screen.Home
import com.example.runalyze.ui.screen.Profile
import com.example.runalyze.ui.screen.runningPlan.RunningPlanDetailView
import com.example.runalyze.ui.screen.runningPlan.RunningPlanListScreen
import com.example.runalyze.viewmodel.GoalViewModel
import com.example.runalyze.viewmodel.RunningPlanViewModel

@Composable
fun RunalyzeApp(
    goalViewModel: GoalViewModel,
    location: LocationDetails?,
) {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(
        navHostController = navController,
        scrollState = scrollState,
        goalViewModel = goalViewModel,
        location = location,
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navHostController: NavHostController,
    scrollState: ScrollState,
    goalViewModel: GoalViewModel,
    location: LocationDetails?,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationMenu(navController = navHostController)
        }
    )
    { innerPadding ->
        Box(
            modifier = Modifier.padding(
                PaddingValues(
                    0.dp,
                    0.dp,
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
            )
        ) {
            Navigation(
                navController = navHostController,
                scrollState = scrollState,
                goalViewModel = goalViewModel,
                location = location,
            )
        }
    }
}

@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    goalViewModel: GoalViewModel,
    location: LocationDetails?,
) {
    val runningPlanViewModel  = RunningPlanViewModel()
    runningPlanViewModel.getRunningPlanList()
    val runningPlanList: List<RunningPlan> by runningPlanViewModel.runningPlanList.observeAsState(
        mutableListOf()
    )
    NavHost(navController = navController, startDestination = "Home") {
        bottomNavigation(runningPlanList, navController = navController)
        composable("home") {
            Home(navController = navController)
        }
        composable("training") {
            RunScreen(navController = navController, location = location)
        }
        composable("goal") {
            AddGoalView(goalViewModel, navController)
        }
        composable("plan") {
            RunningPlanListScreen(runningPlanList, navController = navController)
        }
        composable("runningPlanDetail/{runningPlanId}") { backStackEntry ->
            val planId = backStackEntry.arguments?.getInt("runningPlanId")
            Log.d("aaaa navigateId", planId.toString())
            RunningPlanDetailView(
                backStackEntry.arguments?.getInt("runningPlanId") ?: 1,
                runningPlanList,
                navController
            )
        }
    }
}

fun NavGraphBuilder.bottomNavigation(
    runningPlanList: List<RunningPlan>,
    navController: NavController
) {
    composable(BottomNavItem.Home.route) {
        Home(navController = navController)
    }
    composable(BottomNavItem.Training.route) {
        RunningPlanListScreen(runningPlanList, navController = navController)
    }
    composable(BottomNavItem.Activity.route) {
        Activity()
    }
    composable(BottomNavItem.Profile.route) {
        Profile()
    }
}