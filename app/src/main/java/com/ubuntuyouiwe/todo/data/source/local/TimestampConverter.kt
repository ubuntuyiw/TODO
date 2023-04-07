package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.TypeConverter
import java.sql.Timestamp

class TimestampConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Timestamp? {
        return value?.let { Timestamp(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Timestamp?): Long? {
        return date?.time?.toLong()
    }
}