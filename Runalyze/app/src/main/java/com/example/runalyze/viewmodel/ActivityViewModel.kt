package com.example.runalyze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.TrainingDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDb.getInstance(application).trainingDetailDao
    private val _allTrainings = MutableLiveData<List<TrainingDetail>>()
    val allTrainings = _allTrainings
    private val _count = MutableLiveData<Int>()
    val count = _count

    init {
        getAllTrainingDetails()
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
}