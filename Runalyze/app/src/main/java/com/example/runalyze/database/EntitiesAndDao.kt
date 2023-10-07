package com.example.runalyze.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
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
    val isActive: Boolean
)

@Entity(tableName = "run_table")
data class Run(
    @PrimaryKey(autoGenerate = true)
    val runId: Int = 0,
    val timestamp: Long = 0L,
    val avgSpeedInKMH: Float = 0f,
    val distanceInMeters:Int = 0,
    val durationInMillis: Long = 0L,
    val avgHeartRate: Double = 0.0
)

@Dao
interface GoalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGoal(goal: Goal): Long
}

@Dao
interface RunDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM run_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>
}