package com.example.runalyze.view

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex

@SuppressLint("RememberReturnType", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddGoalView() {
    var openDialog = remember { mutableStateOf(false) }
    val dateRangePickerState = rememberDateRangePickerState()

    val formattedStartDate = dateRangePickerState.selectedStartDateMillis?.let {
        val date = Date(it)
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        dateFormat.format(date)
    } ?: null

    val formattedEndDate = dateRangePickerState.selectedEndDateMillis?.let {
        val date = Date(it)
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        dateFormat.format(date)
    } ?: null

    Column {
        Text("Duration")

        Button(onClick = { openDialog.value = true }) {
            Text(
                text = if (formattedStartDate != null && formattedEndDate != null) {
                    "From $formattedStartDate to $formattedEndDate"
                } else {
                    "Choose training period"
                }
            )
        }
    }

    if (openDialog.value) {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            DateRangePickerDialog(
                state = dateRangePickerState,
                onDismissRequest = { openDialog.value = false },
                onSaveClick = { startDateMillis, endDateMillis ->
                    Log.d(
                        "Runalyze App",
                        "Saved range (timestamps): $startDateMillis..$endDateMillis"
                    )
                    openDialog.value = false
                }
            )
        }
    }

    var targetDates by remember { mutableStateOf("") }
    var reminderTime by remember { mutableLongStateOf(0) }
    var targetDuration by remember { mutableIntStateOf(0) }
    var targetDistance by remember { mutableDoubleStateOf(0.0) }
    var targetSpeed by remember { mutableDoubleStateOf(0.0) }
    var targetHeartRate by remember { mutableIntStateOf(0) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    state: DateRangePickerState,
    onDismissRequest: () -> Unit,
    onSaveClick: (startDateMillis: Long, endDateMillis: Long) -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            modifier = Modifier.requiredWidth(LocalConfiguration.current.screenWidthDp.dp * 0.96f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = onDismissRequest
                ) {
                    Text("Cancel")
                }
                TextButton(
                    onClick = {
                        onSaveClick(
                            state.selectedStartDateMillis ?: return@TextButton,
                            state.selectedEndDateMillis ?: return@TextButton
                        )
                        onDismissRequest()
                    },
                    enabled = state.selectedEndDateMillis != null
                ) {
                    Text("Save")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            DateRangePicker(
                state = state,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}










