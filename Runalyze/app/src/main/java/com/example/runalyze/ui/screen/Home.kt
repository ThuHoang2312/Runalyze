package com.example.runalyze.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.runalyze.R


data class HomeOption (
    val id: Int,
    val image:Int = R.drawable.running_red,
    val text: String,
    val route: String
)

object Options {
    val optionList = listOf<HomeOption>(
        HomeOption(
            id = 1, image = R.drawable.running_red, text = "Start a quick run", route = "Training"
        ),
        HomeOption(
            id = 2, image = R.drawable.running_blue, text = "Choose a training plan", route = "Plan"
        ),
        HomeOption(
            id = 3, image = R.drawable.running_dark, text = "Set a goal", route = "Goal"
        )
    )
}

@Composable
fun Home(navController: NavController){
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        LazyColumn {
            items(Options.optionList){option ->
                HomeItem(option = option, navController = navController)
            }
        }
    }
}

@Composable
fun HomeItem(option: HomeOption, navController: NavController){
    Box(modifier = Modifier
        .height(250.dp).fillMaxSize()){
        Image(painter = painterResource(id = option.image ), contentDescription = "",
                contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
            )
        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Button(onClick = {
                navController.navigate(option.route)
            }){
                Text(text = option.text, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeItem(option = HomeOption(id = 1, image = R.drawable.running_red, text = "Start a quick run", route = "Training"), navController = rememberNavController() )
}