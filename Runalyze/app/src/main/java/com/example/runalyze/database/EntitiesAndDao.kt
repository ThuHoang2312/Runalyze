package com.example.runalyze.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity
data class Goal(
    @PrimaryKey(autoGenerate = true)
    val goalId: Long,
    @TypeConverters(Converters::class)
    val startDate:Long?,
    @TypeConverters(Converters::class)
    val endDate:Long?,
    val repeatedDays: String,
    val reminderTime: String?,
    val targetDistanceInKm: Double,
    val targetSpeedInKmh: Double,
    val targetHeartRateInBpm: Int,
    val isActive: Boolean
)

@Entity
data class TrainingDetail(
    @PrimaryKey
    val trainingDetailId: Long,
    @TypeConverters(Converters::class)
    val trainingDateTime: Date,
    val durationInMinutes: Int,
    val distance: Double,
    val averageSpeed: Double,
    val heartRate: Int,
    val elevation: Double,
    val caloriesInKcal: Int
)

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGoal(goal: Goal): Long
}