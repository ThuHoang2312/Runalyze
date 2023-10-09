package com.example.runalyze.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.R
import com.example.runalyze.ui.componentLibrary.RunStats
import com.example.runalyze.utils.Destination

@Composable
fun RunResult(navController: NavController){

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.finish_run),
                contentDescription = stringResource(id = R.string.finish_image),
                contentScale = ContentScale.Fit,
            )
            Text(
                text = stringResource(id = R.string.congrats),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                fontSize = 25.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                RunStats(
                    text = stringResource(id = R.string.distance),
                    value = "2",
                    unit = stringResource(id = R.string.distance_unit)
                )
                RunStats(
                    text = stringResource(id = R.string.avgSpeed),
                    value = "3.5",
                    unit = stringResource(id = R.string.speed_unit)
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { Destination.RunResultDisplay.navigateToHome(navController)
                    }
                ) {
                    Text(text = stringResource(id = R.string.home_button), fontWeight = FontWeight.SemiBold)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { Destination.RunResultDisplay.navigateToGraph(navController)
                    }
                ) {
                    Text(text = stringResource(id = R.string.stats_button), fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RunResultPreview(){
    val navController = rememberNavController()
    RunResult(navController = navController)
}


