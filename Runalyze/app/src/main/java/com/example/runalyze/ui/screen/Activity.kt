package com.example.runalyze.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.runalyze.components.TopNavigation
import com.example.runalyze.database.Converters
import com.example.runalyze.database.Run
import com.example.runalyze.ui.component.BarChart
import com.example.runalyze.viewmodel.ActivityViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Activity(viewModel: ActivityViewModel, navController: NavController) {
    val allData by viewModel.allTrainings.observeAsState(emptyList())
    var filteredData by remember { mutableStateOf(emptyList<Run>()) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopNavigation(
                text = "Activity",
                navController = navController
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(values),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 64.dp, end = 64.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(onClick = {
                    filteredData = allData.filter {
                        var month = Converters().fromTimestamp(it.timestamp).toString().split("-")[1].toInt()
                        var currentMonth = Calendar.getInstance().get(Calendar.MONTH)

                        month == currentMonth + 1
                    }
                }) {
                    Text(text = "Month")
                }
                TextButton(onClick = {
                    filteredData = allData.filter {
                        var year = Converters().fromTimestamp(it.timestamp).toString().split("-")[0].toInt()
                        var currentYear = Calendar.getInstance().get(Calendar.YEAR)

                        year == currentYear
                    }
                }) {
                    Text(text = "Year")
                }
            }
            val inputData = filteredData.ifEmpty { allData }
            Log.d("Runalyze", "Input data: ${inputData}")
            GraphView(allData = inputData, screenWidth = screenWidth)
        }
    }
}

@Composable
fun GraphView(allData: List<Run>, screenWidth: Dp) {
    Text(text = "Distance")
    Box {
        BarChart(data = allData, screenWidth = screenWidth, key = "distance")
    }
    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "Average Speed")
    Box {
        BarChart(data = allData, screenWidth = screenWidth, key = "speed")
    }
    Spacer(modifier = Modifier.height(50.dp))
}
