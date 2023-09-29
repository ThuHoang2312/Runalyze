package com.example.runalyze.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class RunningPlan (val planId: Int, val name: String, val imageURL: String, val description: String, val duration: String, val frequency: String, val level: String, val heartRate: Int)

object GetRunningPlanApi {
    const val URL = "https://users.metropolia.fi/~thuh/"

    interface Service {
        @GET("RunningPlan.json")
        suspend fun runningPlanList(): List<RunningPlan>
    }

    private val retrofit = Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build()

    val service: Service by lazy {
        retrofit.create(Service::class.java)
    }
}