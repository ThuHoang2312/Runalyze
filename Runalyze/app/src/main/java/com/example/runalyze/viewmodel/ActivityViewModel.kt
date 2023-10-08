package com.example.runalyze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.Run
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDb.getInstance(application).runDao
    private val _allTrainings = MutableLiveData<List<Run>>()
    val allTrainings = _allTrainings
    private val _count = MutableLiveData<Int>()
    val count = _count

    init {
        getAllTrainingDetails()
    }

    fun addDummyData() {
        viewModelScope.launch {
            db.addTrainingDetail(
                Run(
                    0,
                    1695801268,
                    9.0F,
                    9000,
                    60 * 60000,
                    150.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1695887668,
                    12.0F,
                    4000,
                    20 * 60000,
                    157.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1695974068,
                    9.6F,
                    8000,
                    50 * 60000,
                    160.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696060468,
                    10.0F,
                    7500,
                    45 * 60000,
                    152.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696146868,
                    10.0F,
                    5000,
                    30 * 60000,
                    145.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696224721,
                    10.0F,
                    10000,
                    60 * 60000,
                    158.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696311121,
                    16.0F,
                    8000,
                    30 * 60000,
                    164.0
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696397521,
                    10.67F,
                    8000,
                    45 * 60000,
                    160.0
                )
            )
        }
    }

    fun addTrainingDetailLiveData(detail: Run) {
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

    private fun getAllTrainingDetails() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                db.getTrainingDetails()
            }
            _allTrainings.value = data
        }
    }
}