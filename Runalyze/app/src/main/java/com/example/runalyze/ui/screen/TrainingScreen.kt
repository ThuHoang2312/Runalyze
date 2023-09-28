package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.components.TopNavigation
import com.example.runalyze.view.RunningPlanList
import com.example.runalyze.viewmodel.RunningPlanViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen(navController: NavController){
    val runningPlanViewModel = RunningPlanViewModel()
    val navController = rememberNavController()
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold (
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopNavigation(
                    text = "Activity",
                    navController = navController,
                    scrollBehavior = scrollBehavior
                )
            }
        ) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally,

            ) {
                RunningPlanList(model = runningPlanViewModel , navController = navController)
                Button(onClick = {
                    navController.navigate("TrainingProcess")
                }){
                    Text(text = "Start", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
fun TrainingScreenPreview() {
    TrainingScreen(rememberNavController())
}