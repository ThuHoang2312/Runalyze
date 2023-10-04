package com.example.runalyze.ui.component

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.runalyze.database.Converters
import com.example.runalyze.database.TrainingDetail
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BarChart(data: List<TrainingDetail>) {
    Log.d("Runalyze", "Data for bar chart: $data")
    var maxValue by remember { mutableDoubleStateOf(0.0) }

    data.map {
        maxValue = maxOf(it.distance, maxValue)
    }

    Canvas(modifier = Modifier.width(275.dp).height(300.dp)) {
        val barWidth = size.width / data.size
        val barSpacing = 16.dp.toPx()
        val maxBarHeight = size.height - 32.dp.toPx()

        data.forEachIndexed { index, trainingDetail ->
            val barHeight = (trainingDetail.distance / maxValue) * maxBarHeight
            val x = index * (barWidth + barSpacing)
            val y = size.height - barHeight
            val ratingColor = getRatingColor(rating = trainingDetail.rating ?: 0)

            drawRect(
                color = ratingColor,
                topLeft = Offset(x, y.toFloat()),
                size = Size(barWidth, barHeight.toFloat()),
                style = Stroke(2.dp.toPx())
            )

            val dateFormatter = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateTime = dateFormatter.format(Converters().fromTimestamp(trainingDetail.trainingDateTime))
            val textWidth = with(density) { dateTime.length * 2.dp.toPx()}

            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    dateTime,
                    x - textWidth / 2,
                    size.height + 16.dp.toPx(),
                    Paint().apply {
                        color = Color.Black.hashCode()
                        textSize = 16.dp.toPx()
                    }
                )
            }
        }
    }
}

fun getRatingColor(rating: Int): Color {
    val ratingColorMap = mapOf(
        1 to Color.Red,
        2 to Color.Yellow,
        3 to Color.Blue,
        4 to Color.Cyan,
        5 to Color.Green
    )

    return ratingColorMap[rating] ?: Color.Gray
}

