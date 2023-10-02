package com.example.runalyze.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.runalyze.R
import com.example.runalyze.componentLibrary.TextModifiedWithPaddingStart
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.viewmodel.RunningPlanViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import kotlin.math.log

@Composable
fun RunningPlanList(model: RunningPlanViewModel, navController: NavController) {
    model.getRunningPlanList()
    val runningPlanList: List<RunningPlan> by model.runningPlanList.observeAsState(mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        if (runningPlanList.isNotEmpty()) {
            runningPlanList.forEach { runningPlan ->
                RunningPlanListItem(runningPlan = runningPlan, navController = navController)
                Log.d("aaaa", runningPlan.toString())
            }

        } else {
            Text("Empty")
        }
    }

}

@Composable
fun RunningPlanListItem(runningPlan: RunningPlan, navController: NavController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = runningPlan.name,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
                RunningPlanItemDetail(Icons.Filled.CalendarMonth, "Duration", runningPlan.duration)
                RunningPlanItemDetail(
                    Icons.AutoMirrored.Filled.DirectionsRun,
                    "Frequency",
                    runningPlan.frequency
                )
                RunningPlanItemDetail(Icons.Filled.StackedLineChart, "Level", runningPlan.level)

            }
            AsyncImage(
                model = runningPlan.imageUrl,
                contentDescription = "Running illustration",
                modifier = Modifier
                    .weight(1f)
                    .size(width = 150.dp, height = 150.dp)
            )
        }
    }
}

@Composable
fun RunningPlanItemDetail(icon: ImageVector, contentDescription: String, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(14.dp)
        )
        TextModifiedWithPaddingStart(label, color = Color.Black, size = 12)
    }
}

//private suspend fun getImage(context: Context, url: URL): Bitmap? = withContext(Dispatchers.IO) {
//    val TAG = "Runalyze"
//    if (isNetworkAvailable(context = context)) {
//        try {
//            val connection = url.openConnection() as HttpURLConnection
//            connection.doInput = true
//            connection.connect()
//            val input: InputStream = connection.inputStream
//            return@withContext BitmapFactory.decodeStream(input)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    } else Log.d(TAG, "Network is not available")
//    return@withContext null
//}

//@Composable
//fun ShowPicture(url: URL): Bitmap? {
//    var text by remember { mutableStateOf("") }
//    LaunchedEffect(urlText) {
//        text = getText(urlText)
//    } Text(text)
//}


@SuppressLint("ServiceCast", "MissingPermission")
fun isNetworkAvailable(context: Context): Boolean {
    val connectionManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkCapabilities =
        connectionManager.getNetworkCapabilities(connectionManager.activeNetwork)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        networkCapabilities?.let { networkCapability ->
            return networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapability.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
    } else {
        return networkCapabilities != null && networkCapabilities.hasCapability(
            NetworkCapabilities.NET_CAPABILITY_INTERNET
        )
    }
    return false
}
