

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.runalyze.components.RunStatsCard
import com.example.runalyze.service.location.LocationDetails
import com.example.runalyze.service.location.models.CurrentRunState
import com.example.runalyze.utils.LocationUtils
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun RunScreen(
    navController: NavController,
    location: LocationDetails?,
) {
    var mapSize by remember { mutableStateOf(Size(0f, 0f)) }
    var mapCenter by remember { mutableStateOf(Offset(0f, 0f)) }
    var isMapLoaded by remember { mutableStateOf(false) }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                mapToolbarEnabled = false,
                compassEnabled = true,
                zoomControlsEnabled = false
            )
        )
    }
    var runState = CurrentRunState()
    var isRunning = false

    val context = LocalContext.current
    val currentLocation = location?.let {
        LatLng(location.latitude.toDouble(), location.longtitude.toDouble())
    }
    val cameraPositionState = rememberCameraPositionState{
        position = CameraPosition.fromLatLngZoom(currentLocation!!, 10f)
    }

    LaunchedEffect(key1 = true) {
        LocationUtils.checkAndRequestLocationSetting(context as Activity)
    }


    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize().drawBehind {
                mapSize = size
                mapCenter = center
            },
            uiSettings = mapUiSettings,
            cameraPositionState = cameraPositionState,
            onMapLoaded = { isMapLoaded = true },
        ) {
            Marker (
            state = rememberMarkerState(position = currentLocation!!),
            title = "you are here",
            snippet = "${location?.latitude}, ${location?.longtitude}"
            )
        }
        RunStatsCard(
            durationInMillis = 0L,
            runState = runState,
            onStartPauseButtonClick = {isRunning = true},
            onFinish = {isRunning = !isRunning}
        )

    }
}
