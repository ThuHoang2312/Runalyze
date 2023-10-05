package com.example.runalyze.ui.component

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.runalyze.database.Converters
import com.example.runalyze.database.TrainingDetail
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun BarChart(data: List<TrainingDetail>, key: String, screenWidth: Dp) {
    Log.d("Runalyze", "Data for bar chart: $data")
    var maxValue by remember { mutableDoubleStateOf(0.0) }

    data.map {
        var graphFilter: Double = when (key) {
            "distance" -> it.distance
            "speed" -> it.averageSpeed
            else -> it.heartRate?.toDouble() ?: 0.0
        }
        maxValue = maxOf(graphFilter, maxValue)
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(8.dp)
    ) {
        val barSpacing = 2.dp.toPx()
        val barCount = data.size
        val maxLabelWidth = with(density) { (maxValue.toString().length) }
        //val barWidth = ((size.width - maxLabelWidth - 32.dp.toPx()) - (barSpacing * (barCount + 1))) / barCount
        val barWidth =
            (screenWidth.toPx() - maxLabelWidth - (barSpacing * (barCount + 1))) / (barCount + 1)
        val maxBarHeight = size.height - 32.dp.toPx()
        val yLabelStep = (maxValue / 5).roundToInt()

        for (i in 0..5) {
            val label = (yLabelStep * i).toString()
            val yLabelPosition = size.height - (i * maxBarHeight / 5)

            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    label,
                    maxLabelWidth.toFloat(),
                    yLabelPosition,
                    Paint().apply {
                        color = Color.Black.hashCode()
                        textSize = 16.dp.toPx()
                    }
                )
            }
        }

        data.forEachIndexed { index, trainingDetail ->
            var graphFilter: Double = when (key) {
                "distance" -> trainingDetail.distance
                "speed" -> trainingDetail.averageSpeed
                else -> trainingDetail.heartRate?.toDouble() ?: 0.0
            }
            val barHeight = (graphFilter.div(maxValue)).times(maxBarHeight)
            val x = (index + 0.75) * (barWidth + barSpacing)
            val y = size.height - barHeight
            val ratingColor = getRatingColor(rating = trainingDetail.rating ?: 0)

            drawRect(
                color = ratingColor,
                topLeft = Offset(x.toFloat(), y.toFloat()),
                size = Size(barWidth, barHeight.toFloat()),
            )

            val dateFormatter = SimpleDateFormat("dd/MM", Locale.getDefault())
            val dateTime =
                dateFormatter.format(Converters().fromTimestamp(trainingDetail.trainingDateTime))
            val textWidth = with(density) { dateTime.length * 2.dp.toPx() }

            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    dateTime,
                    ((x - textWidth / 2).toFloat()),
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

