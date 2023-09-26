package com.example.runalyze.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun TrainingScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            navController.navigate("TrainingProcess")
        }){
            Text(text = "Start", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrainingScreenPreview() {
    TrainingScreen(rememberNavController())
}