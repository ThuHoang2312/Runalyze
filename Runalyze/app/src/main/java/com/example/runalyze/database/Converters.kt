package com.example.runalyze.database

import android.util.Log
import androidx.room.TypeConverter
import java.sql.Date

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        Log.d("Runalyze", "Time stamp to Date: $value - ${value?.let { Date(it) }}")
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}