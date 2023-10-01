package com.example.runalyze.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun BarChart(data: List<Double>) {
    var maxValue by remember { mutableDoubleStateOf(0.0) }

    data.map {
        maxValue = maxOf(it, maxValue)
    }

    Canvas(modifier = Modifier.fillMaxSize()) {
        val barWidth = size.width / data.size
        val barSpacing = 16.dp.toPx()
        val maxBarHeight = size.height - 32.dp.toPx()

        data.forEachIndexed { index, value ->
            val barHeight = (value / maxValue) * maxBarHeight
            val x = index * (barWidth + barSpacing)
            val y = size.height - barHeight

            drawRect(
                color = Color.Blue,
                topLeft = Offset(x, y.toFloat()),
                size = Size(barWidth, barHeight.toFloat()),
                style = Stroke(2.dp.toPx())
            )
        }
    }
}

