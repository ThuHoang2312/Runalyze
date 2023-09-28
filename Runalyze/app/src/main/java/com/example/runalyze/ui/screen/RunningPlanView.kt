package com.example.runalyze.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ShowChart
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runalyze.R
import com.example.runalyze.componentLibrary.TextModifiedWithPaddingStart
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.ui.theme.RunalyzeTheme

@Composable
fun RunningPlanList(runningPlanList: List<RunningPlan>, navController: NavController) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        runningPlanList.forEach { runningPlan ->
            RunningPlanListItem(runningPlan = runningPlan, navController = navController)
        }
    }

}

@Composable
fun RunningPlanListItem(runningPlan: RunningPlan, navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(
                    text = runningPlan.name,
                    fontSize = 14.sp,
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
            Image(
                painter = painterResource(R.drawable.testing),
                contentDescription = "Image",
                modifier = Modifier.size(width = 150.dp, height = 100.dp)
            )
        }

    }
}

@Composable
fun RunningPlanItemDetail(icon: ImageVector, contentDescription: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(14.dp)
        )
        TextModifiedWithPaddingStart(label, color = Color.Black, size = 12)
    }
}
