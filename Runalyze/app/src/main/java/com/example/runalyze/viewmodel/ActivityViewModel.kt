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
                    60,
                    9000,
                    9.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1695887668,
                    20,
                    4000,
                    12.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1695974068,
                    50,
                    8000,
                    9.6F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696060468,
                    45,
                    7500,
                    10.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696146868,
                    30,
                    5000,
                    10.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696224721,
                    60,
                    10000,
                    10.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696311121,
                    30,
                    8000,
                    16.0F
                )
            )
            db.addTrainingDetail(
                Run(
                    0,
                    1696397521,
                    45,
                    8000,
                    10.67F
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