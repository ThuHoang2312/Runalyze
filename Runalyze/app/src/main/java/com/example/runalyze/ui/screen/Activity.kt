package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.runalyze.viewmodel.ActivityViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.runalyze.ui.component.BarChart

@Composable
fun Activity(viewModel: ActivityViewModel) {
    val allData by viewModel.alTrainings.observeAsState(emptyList())
    val distanceData = allData.map { it.distance }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarChart(data = distanceData)
    }
}

