package com.example.runalyze.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runalyze.service.GetRunningPlanApi
import com.example.runalyze.service.RunningPlan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WebServiceRepository() {
    private val call = GetRunningPlanApi.service
    suspend fun getRunningPlan() = call.runningPlanList()
}

class RunningPlanViewModel: ViewModel() {
    private val repository: WebServiceRepository = WebServiceRepository()

    val runningPlanList = MutableLiveData<List<RunningPlan>>()
    fun getRunningPlanList() {
        viewModelScope.launch (Dispatchers.IO){
            val list = repository.getRunningPlan()
            runningPlanList.postValue(list)
        }
    }
}