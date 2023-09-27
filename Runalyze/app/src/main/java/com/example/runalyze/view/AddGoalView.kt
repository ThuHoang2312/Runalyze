package com.example.runalyze.view

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.runalyze.ui.theme.RunalyzeTheme
import java.util.Calendar
import androidx.compose.material3.TimePickerState
import androidx.compose.ui.graphics.Color

@SuppressLint("RememberReturnType", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddGoalView() {
    var openDateRangePickerDialog = remember { mutableStateOf(false) }
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
    var openTimePickerDialog by remember { mutableStateOf(false) }
    var timePickerState = rememberTimePickerState()
    val isReminderTimeSet = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = "Set a goal",
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Duration: ",
                fontWeight = FontWeight.Bold
            )
            Button(
                onClick = { openDateRangePickerDialog.value = true },
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = if (formattedStartDate != null && formattedEndDate != null) {
                        "From $formattedStartDate to $formattedEndDate"
                    } else {
                        "Choose training period"
                    }
                )
            }
        }
        Text(
            text = "Repeat: ",
            fontWeight = FontWeight.Bold
        )
        DayOfWeekSelection()

        val formatter = remember { SimpleDateFormat("hh:mm a", Locale.getDefault()) }
        val cal = Calendar.getInstance()

        cal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
        cal.set(Calendar.MINUTE, timePickerState.minute)
        cal.isLenient = false

        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Reminder: ",
                    fontWeight = FontWeight.Bold
                )
                Log.d("Runalyze", "Entered time: ${formatter.format(cal.time)}")
                Log.d("Runalyze", "Entered time: ${timePickerState.hour}:${timePickerState.hour}")
                Spacer(modifier = Modifier.size(8.dp))
                if (isReminderTimeSet.value) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(1.dp, Color.Gray, shape = CircleShape)
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "${formatter.format(cal.time)}")
                            Spacer(modifier = Modifier.size(8.dp))
                            IconButton(
                                modifier = Modifier.size(20.dp),
                                onClick = {
                                    isReminderTimeSet.value = false
                                }) {
                                Icon(Icons.Filled.Close, contentDescription = "Delete reminder")
                            }
                        }
                    }
                } else {
                    Button(
                        onClick = { openTimePickerDialog = true },
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(text = "Add Reminder")
                    }
                }
            }
        }
    }

    if (openDateRangePickerDialog.value) {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            DateRangePickerDialog(
                state = dateRangePickerState,
                onDismissRequest = { openDateRangePickerDialog.value = false },
                onSaveClick = { startDateMillis, endDateMillis ->
                    Log.d(
                        "Runalyze App",
                        "Saved range (timestamps): $startDateMillis..$endDateMillis"
                    )
                    openDateRangePickerDialog.value = false
                }
            )
        }
    }



    if (openTimePickerDialog) {
        Surface {
            TimePickerDialog(
                state = timePickerState,
                onDismissRequest = { openTimePickerDialog = false },
                onSaveClick = {
                    isReminderTimeSet.value = true
                    openTimePickerDialog = false
                }
            )
        }
    }
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

@Composable
fun DayOfWeekSelection() {
    val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    val selectedDays =
        remember { mutableStateListOf<Boolean>(false, false, false, false, false, false, false) }

    LazyRow(
        modifier = Modifier.height(75.dp)
    ) {
        itemsIndexed(daysOfWeek) { index, day ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(4.dp)
            ) {
                Text(text = day, modifier = Modifier.weight(1f))
                Checkbox(
                    checked = selectedDays[index],
                    onCheckedChange = { isChecked ->
                        selectedDays[index] = isChecked
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    state: TimePickerState,
    onDismissRequest: () -> Unit,
    onSaveClick: () -> Unit
) {
    Dialog (
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
                        onSaveClick()
                        onDismissRequest()
                    },
                    enabled = state.minute != null
                ) {
                    Text("Save")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TimePicker(
                state = state,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddGoalPreview() {
    RunalyzeTheme {
        AddGoalView()
    }
}








