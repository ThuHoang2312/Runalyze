package com.example.runalyze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.Goal
import com.example.runalyze.database.Run
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDb.getInstance(application)
    val allTrainings: LiveData<List<Run>> = db.runDao.getTrainingDetails()

    fun addDummyData() {
        viewModelScope.launch {
            db.runDao.addRun(
                Run(
                    0,
                    1695801268000,
                    9.0F,
                    9000,
                    60 * 60000,
                    150.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1695801268000,
                    9.0F,
                    9000,
                    60 * 60000,
                    150.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1695887668000,
                    12.0F,
                    4000,
                    20 * 60000,
                    157.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1695887668000,
                    12.0F,
                    4000,
                    20 * 60000,
                    157.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1695974068000,
                    9.6F,
                    8000,
                    50 * 60000,
                    160.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696060468000,
                    10.0F,
                    7500,
                    45 * 60000,
                    152.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696146868000,
                    10.0F,
                    5000,
                    30 * 60000,
                    145.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696224721000,
                    10.0F,
                    10000,
                    60 * 60000,
                    158.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696311121000,
                    16.0F,
                    8000,
                    30 * 60000,
                    164.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696397521000,
                    10.67F,
                    8000,
                    45 * 60000,
                    160.0
                )
            )
            db.runDao.addRun(
                Run(
                    0,
                    1696755109000,
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
            db.runDao.addRun(detail)
        }
    }

    fun getNewestGoal(): LiveData<Goal?> = db.goalDao.getLatestGoal()
}



