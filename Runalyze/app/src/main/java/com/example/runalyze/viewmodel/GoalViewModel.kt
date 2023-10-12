package com.example.runalyze.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.runalyze.database.AppDb
import com.example.runalyze.database.Goal
import kotlinx.coroutines.launch

class GoalViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDb.getInstance(application).goalDao

    fun addGoalLiveData(goal: Goal) {
        viewModelScope.launch {
            db.addGoal(goal)
        }
    }
}