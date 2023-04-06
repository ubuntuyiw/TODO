package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto

@Database(entities = [TodoDto::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}