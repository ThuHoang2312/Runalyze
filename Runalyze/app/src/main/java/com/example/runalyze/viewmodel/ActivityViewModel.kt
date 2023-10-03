package com.example.runalyze.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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
    val alTrainings = _allTrainings
    private val _count = MutableLiveData<Int>()
    val count = _count

    init {
        //addDummyData()
        getAllTrainingDetails()
        //getDistanceData()
    }

    fun addDummyData() {
        Log.d("RynalyzeApp", "Training Detail Count: ${count.value}")
        if (count.value == null) {
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
            }
        }
    }

    fun addTrainingDetailLiveData(detail: TrainingDetail) {
        viewModelScope.launch {
            db.addTrainingDetail(detail)

        }
    }

    fun getAllTrainingDetails() {
        viewModelScope.launch {
            val data = withContext(Dispatchers.IO) {
                db.getTrainingDetails() // Replace with your DAO function to fetch all data
            }
            _allTrainings.value = data
        }
    }

    fun getDistanceData() {
        viewModelScope.launch {
            val count = withContext(Dispatchers.IO) {
                db.getTrainingDetailCount() // Replace with your DAO function to count rows
            }
            _count.value = count
        }
    }
}