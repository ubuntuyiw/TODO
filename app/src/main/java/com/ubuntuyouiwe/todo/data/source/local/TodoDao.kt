package com.ubuntuyouiwe.todo.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoDto)

    @Delete
    suspend fun deleteTodo(todo: TodoDto)

    // todo paging3 eklenecek
    @Query("SELECT * FROM todo")
    fun getAllTodo(): List<TodoDto>



    @Query("SELECT * FROM todo WHERE id = :todoId")
    fun getTodo(todoId: Int): Flow<TodoDto>



}