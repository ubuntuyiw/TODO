package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto

@Database(entities = [TodoDto::class], version = 1, exportSchema = true)
@TypeConverters(TimestampConverter::class)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {

        const val DATABASE_NAME = "todo_db"


    }
}