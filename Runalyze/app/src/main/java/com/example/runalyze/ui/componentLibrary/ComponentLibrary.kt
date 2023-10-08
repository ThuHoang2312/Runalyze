package com.example.runalyze.ui.componentLibrary

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
// View for detail in running plan item
fun RunningPlanItemDetail(icon: ImageVector, contentDescription: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(18.dp)
        )
        TextModifiedWithPaddingStart(label, size = 16)
    }
}

// TextView
@Composable
fun TextModifiedWithPaddingStart(
    string: String,
    size: Int = 12,
    paddingStart: Int = 5,
) {
    Text(
        text = string,
        fontSize = size.sp,
        modifier = Modifier.padding(start = paddingStart.dp)
    )
}


