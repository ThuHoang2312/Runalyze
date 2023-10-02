package com.example.runalyze.ui.screen.runningPlan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.runalyze.componentLibrary.TextModifiedWithPaddingStart
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.viewmodel.RunningPlanViewModel

@Composable
// Display running plan list fetch from network
fun RunningPlanList(model: RunningPlanViewModel, navController: NavController) {
    model.getRunningPlanList()
    val runningPlanList: List<RunningPlan> by model.runningPlanList.observeAsState(mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        if (runningPlanList.isNotEmpty()) {
            runningPlanList.forEach { runningPlan ->
                RunningPlanListItem(runningPlan = runningPlan, navController = navController)
            }

        } else {
            Text("Empty")
        }
    }

}

@Composable
// View for item in running plan list
fun RunningPlanListItem(runningPlan: RunningPlan, navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = runningPlan.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                RunningPlanItemDetail(Icons.Filled.CalendarMonth, "Duration", runningPlan.duration)
                RunningPlanItemDetail(
                    Icons.AutoMirrored.Filled.DirectionsRun,
                    "Frequency",
                    runningPlan.frequency
                )
                RunningPlanItemDetail(Icons.Filled.StackedLineChart, "Level", runningPlan.level)

            }
            AsyncImage(
                model = runningPlan.imageUrl,
                contentDescription = "Running illustration",
                modifier = Modifier
                    .weight(1f)
                    .size(width = 150.dp, height = 150.dp)
            )
        }
    }
}

@Composable
// View for detail in running plan item
fun RunningPlanItemDetail(icon: ImageVector, contentDescription: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(14.dp)
        )
        TextModifiedWithPaddingStart(label, size = 12)
    }
}
