package com.example.runalyze

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.runalyze.components.LocationPermissionRequestDialog
import com.example.runalyze.service.RunningPlan
import com.example.runalyze.ui.RunalyzeApp
import com.example.runalyze.ui.theme.RunalyzeTheme
import com.example.runalyze.utils.LocationUtils
import com.example.runalyze.utils.RunUtils
import com.example.runalyze.utils.RunUtils.hasAllPermission
import com.example.runalyze.utils.RunUtils.hasLocationPermission
import com.example.runalyze.utils.RunUtils.openAppSetting
import com.example.runalyze.viewmodel.GoalViewModel
import com.example.runalyze.viewmodel.RunViewModel
import com.example.runalyze.viewmodel.RunningPlanViewModel

class MainActivity : ComponentActivity() {
    private val goalViewModel: GoalViewModel by viewModels()
    private val runViewModel: RunViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* TODO  add things need to be done here when app is loading */
        installSplashScreen()

        setContent {
            val location by runViewModel.getLocationData().observeAsState()

            RunalyzeTheme {
                PermissionRequester()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RunalyzeApp(  goalViewModel, location)
                }
            }
        }

    }

    @Composable
    private fun PermissionRequester() {
        var showPermissionDeclinedRationale by rememberSaveable { mutableStateOf(false) }
        var showRationale by rememberSaveable { mutableStateOf(false) }
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                it.forEach { (permission, isGranted) ->
                    if (!isGranted && RunUtils.locationPermissions.contains(permission)) {
                        showPermissionDeclinedRationale = true
                    }
                }
            }
        )
        if (showPermissionDeclinedRationale)
            LocationPermissionRequestDialog(
                onDismissClick = {
                    if (!hasLocationPermission())
                        finish()
                    else showPermissionDeclinedRationale = false
                },
                onOkClick = { openAppSetting() }
            )
        if (showRationale)
            LocationPermissionRequestDialog(
                onDismissClick = ::finish,
                onOkClick = {
                    showRationale = false
                    permissionLauncher.launch(RunUtils.allPermissions)
                }
            )
        LaunchedEffect(key1 = Unit) {
            when {
                hasAllPermission() -> return@LaunchedEffect
                RunUtils.locationPermissions.any { shouldShowRequestPermissionRationale(it) } -> showRationale =
                    true

                else -> permissionLauncher.launch(RunUtils.allPermissions)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LocationUtils.LOCATION_ENABLE_REQUEST_CODE && resultCode != Activity.RESULT_OK) {
            Toast.makeText(
                this,
                "Please enable GPS to get running statistics.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RunalyzeTheme {
        RunalyzeApp()
    }
}