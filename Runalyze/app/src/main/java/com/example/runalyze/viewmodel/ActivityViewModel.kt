package com.example.runalyze.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.TrainingDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDb.getInstance(application).trainingDetailDao
    private val _allTrainings = MutableLiveData<List<TrainingDetail>>()
    val alTrainings = _allTrainings
    private val _allTrainingsByWeek = MutableLiveData<List<TrainingDetail>>()
    val alTrainingsByWeek = _allTrainingsByWeek
    private val _allTrainingsByMonth = MutableLiveData<List<TrainingDetail>>()
    val alTrainingsByMonth = _allTrainingsByMonth
    private val _count = MutableLiveData<Int>()
    val count = _count

    init {
        //addDummyData()
        getAllTrainingDetails()
        alTrainings.value?.let { filterTrainingByWeek(it) }
        alTrainings.value?.let { filterTrainingByMonth(it) }
        //getDistanceData()
    }

    fun addDummyData() {
        viewModelScope.launch {
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1695801268,
                    60,
                    9.0,
                    9.0,
                    155,
                    null,
                    null,
                    3,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1695887668,
                    20,
                    4.0,
                    12.0,
                    160,
                    null,
                    null,
                    5,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1695974068,
                    50,
                    8.0,
                    9.6,
                    140,
                    null,
                    null,
                    5,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1696060468,
                    45,
                    7.5,
                    10.0,
                    145,
                    null,
                    null,
                    3,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1696146868,
                    30,
                    5.0,
                    10.0,
                    150,
                    null,
                    null,
                    4,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1696224721,
                    60,
                    10.0,
                    10.0,
                    150,
                    null,
                    null,
                    3,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1696311121,
                    30,
                    8.0,
                    16.0,
                    160,
                    null,
                    null,
                    4,
                    null
                )
            )
            db.addTrainingDetail(
                TrainingDetail(
                    0,
                    1696397521,
                    45,
                    8.0,
                    10.67,
                    150,
                    null,
                    null,
                    5,
                    null
                )
            )
        }
    }

    fun addTrainingDetailLiveData(detail: TrainingDetail) {
        viewModelScope.launch {
            db.addTrainingDetail(detail)

        }
    }

    fun getTrainingDetailsCount() {
        viewModelScope.launch {
            val currentCount = db.getTrainingDetailCount()
            _count.value = currentCount
        }
    }

    fun getAllTrainingDetails() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                db.getTrainingDetails()
            }
            _allTrainings.value = data
        }
    }

    fun filterTrainingByWeek(data: List<TrainingDetail>) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance()
            val currentWeek = currentDate.get(Calendar.WEEK_OF_YEAR)

            val newData = data.filter { trainingDetail ->
                val trainingDate = Calendar.getInstance()
                trainingDate.timeInMillis = trainingDetail.trainingDateTime
                Log.d(
                    "Runalyze",
                    "${trainingDate.timeInMillis} - ${trainingDetail.trainingDateTime}"
                )
                trainingDate.get(Calendar.WEEK_OF_YEAR) == currentWeek
            }
            withContext(Dispatchers.Main) {
                _allTrainingsByWeek.value = newData
            }
        }
    }

    fun filterTrainingByMonth(data: List<TrainingDetail>) {
        viewModelScope.launch {
            val currentDate = Calendar.getInstance()
            val currentMonth = currentDate.get(Calendar.MONTH)

            _allTrainingsByMonth.value = data.filter { trainingDetail ->
                val trainingDate = Calendar.getInstance()
                trainingDate.timeInMillis = trainingDetail.trainingDateTime

                trainingDate.get(Calendar.MONTH) == currentMonth
            }
            Log.d("Runalyze", "Current month: ${currentMonth}")
        }
    }
}