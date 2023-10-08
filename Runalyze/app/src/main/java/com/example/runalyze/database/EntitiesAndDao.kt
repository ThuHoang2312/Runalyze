package com.example.runalyze.database

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverters

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
    @TypeConverters(Converters::class)
    val createdDate: Long
)

@Entity(tableName = "run_table")
data class Run(
    @PrimaryKey(autoGenerate = true)
    val runId: Long = 0L,
    @TypeConverters(Converters::class)
    val timestamp: Long = 0L,
    val durationInMillis: Long = 0L,
    val distanceInMeters: Int = 0,
    val averageSpeedInKMH: Float = 0f
)
@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGoal(goal: Goal): Long
}

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTrainingDetail(detail: Run): Long

    @Query("SELECT COUNT(*) FROM run_table")
    suspend fun getTrainingDetailCount(): Int

    @Query("SELECT * FROM run_table")
    suspend fun getTrainingDetails(): List<Run>
}