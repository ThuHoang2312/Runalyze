package com.example.runalyze.ui.screen.runningPlan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.runalyze.R
import com.example.runalyze.ui.componentLibrary.TopNavigation
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.ui.componentLibrary.ScreenHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// View for running plan screen
fun RunningPlanListScreen(runningPlanList: List<RunningPlan>, navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                ScreenHeader(stringResource(id = R.string.running_plans_header))
            }) { values ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(values),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                RunningPlanList(runningPlanList = runningPlanList, navController = navController)
            }
        }
    }
}