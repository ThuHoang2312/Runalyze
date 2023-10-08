package com.example.runalyze.ui.screen.runningPlan

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.runalyze.ui.componentLibrary.RunningPlanItemDetail
import com.example.runalyze.ui.components.TopNavigation
import com.example.runalyze.service.RunningPlan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// View for detail in running plan detail
fun RunningPlanDetailView(
    planId: Int,
    runningPlanList: List<RunningPlan>,
    navController: NavController
) {
    Log.d("aaaa list", runningPlanList.toString())
    Log.d("aaaa id", planId.toString())
    if (runningPlanList.isNotEmpty()) {
        val runningPlan = runningPlanList.elementAt(planId)
        Log.d("aaaa", runningPlan.toString())
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
                        text = runningPlan.name,
                        navController = navController,
                    )
                }
            ) { values ->
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(values)
                        .verticalScroll(rememberScrollState())
                        .padding(20.dp)
                ) {
                    AsyncImage(
                        model = runningPlan.imageUrl,
                        contentDescription = "Running illustration",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)) {
                        RunningPlanItemDetail(
                            Icons.Filled.CalendarMonth,
                            "Duration",
                            runningPlan.duration
                        )
                        RunningPlanItemDetail(
                            Icons.AutoMirrored.Filled.DirectionsRun,
                            "Frequency",
                            runningPlan.frequency
                        )
                        RunningPlanItemDetail(
                            Icons.Filled.StackedLineChart,
                            "Level",
                            runningPlan.level
                        )
                    }
                    Text(
                        text = "Planning overview",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(5.dp),
                        fontSize = 22.sp
                    )
                    Text(text = runningPlan.description, modifier = Modifier.padding(5.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Button(onClick = {
                            navController.navigate("goal")
                        }) {
                            Text(text = "Set goal with this plan", fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    } else {
        Text(text = "Loading")
    }

}