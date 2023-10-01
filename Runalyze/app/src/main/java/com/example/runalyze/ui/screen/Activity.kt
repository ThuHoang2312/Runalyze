package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import com.example.runalyze.viewmodel.ActivityViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.runalyze.ui.component.BarChart

@Composable
fun Activity(viewModel: ActivityViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BarChart(data = viewModel.distanceData.value)
    }
}

