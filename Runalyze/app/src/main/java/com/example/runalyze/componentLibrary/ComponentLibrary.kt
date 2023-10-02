package com.example.runalyze.componentLibrary

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
//    color: Color = ,
) {
    Text(
        text = string,
//        color = color,
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