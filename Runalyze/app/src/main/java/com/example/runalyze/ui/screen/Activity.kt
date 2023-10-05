package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.runalyze.components.TopNavigation
import com.example.runalyze.ui.component.BarChart
import com.example.runalyze.viewmodel.ActivityViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Activity(viewModel: ActivityViewModel, navController: NavController) {
    val allData by viewModel.alTrainings.observeAsState(emptyList())
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopNavigation(
                text = "Activity",
                navController = navController,
//                scrollBehavior = scrollBehavior
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                //.requiredHeight(LocalConfiguration.current.screenHeightDp.dp * 0.96f)
                .padding(values),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Distance")
            Box {
                BarChart(data = allData, screenWidth = screenWidth, key = "distance")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Average Speed")
            Box {
                BarChart(data = allData, screenWidth = screenWidth, key = "speed")
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Heart Rate")
            Box {
                BarChart(data = allData, screenWidth = screenWidth, key = "heartRate")
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

