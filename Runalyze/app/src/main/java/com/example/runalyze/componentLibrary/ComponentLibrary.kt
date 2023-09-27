package com.example.runalyze.componentLibrary

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// TopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(navController: NavController, title: String) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back button"
                    )
                }
            }
        }

    )

}

// TextView
@Composable
fun TextModifiedWithId(
    text: String,
    size: Int = 14,
    color: Color = Color.White,
//    font: FontFamily = regular,
) {
    Text(
        text = text,
        color = color,
//        fontFamily = font,
        fontSize = size.sp
    )
}

@Composable
fun TextModifiedWithString(
    string: String,
    size: Int = 14,
//    font: FontFamily = semibold,
    color: Color = Color.White,
) {
    Text(
        text = string,
        color = color,
//        fontFamily = font,
        fontSize = size.sp
    )
}

@Composable
fun TextModifiedWithPaddingStart(
    string: String,
    size: Int = 12,
//    font: FontFamily = semibold,
    paddingStart: Int = 5,
    color: Color = Color.White,
) {
    Text(
        text = string,
        color = color,
//        fontFamily = font,
        fontSize = size.sp,
        modifier = Modifier.padding(start = paddingStart.dp)
    )
}

@Composable
fun TextModifiedWithPaddingTop(string: String?) {
    string?.let {
        Text(
            text = it,
            color = Color.White,
//            fontFamily = semibold,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
fun TextModifiedWithPadding(id: Int) {
    Text(
        stringResource(id = id),
        color = Color.White,
        fontSize = 18.sp,
//        fontFamily = semibold,
        modifier = Modifier.padding(top = 30.dp, start = 30.dp)
    )
}

@Composable
fun TextModifiedStringWithPadding(string: String) {
    Text(
        text = string,
//        color = smallText,
        fontSize = 14.sp,
//        fontFamily = regular,
        modifier = Modifier.padding(top = 5.dp, start = 30.dp)
    )
}