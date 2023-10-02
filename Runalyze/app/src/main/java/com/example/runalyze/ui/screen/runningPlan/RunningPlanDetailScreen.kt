package com.example.runalyze.ui.screen.runningPlan

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.components.TopNavigation
import com.example.runalyze.viewmodel.RunningPlanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// View for running plan screen
fun RunningPlanDetailScreen(navController: NavController) {
    val runningPlanViewModel = RunningPlanViewModel()
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopNavigation(
                    text = "Running plan",
                    navController = navController,
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
//                RunningPlanList(model = runningPlanViewModel, navController = navController)
                Text(text = "Detail")
            }
        }
    }
}