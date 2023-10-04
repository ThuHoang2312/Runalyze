package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.example.runalyze.ui.component.BarChart
import com.example.runalyze.viewmodel.ActivityViewModel

@Composable
fun Activity(viewModel: ActivityViewModel) {
    val allData by viewModel.alTrainings.observeAsState(emptyList())
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier = Modifier.fillMaxSize(),
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
    }
}

