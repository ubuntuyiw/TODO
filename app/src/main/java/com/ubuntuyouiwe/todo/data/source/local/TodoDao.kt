package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoDto)

    @Delete
    suspend fun deleteTodo(todo: TodoDto)


    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<TodoDto>>



}