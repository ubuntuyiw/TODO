package com.ubuntuyouiwe.todo.domain.repositroy

import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    suspend fun insertTodo(todo: TodoDomain)

    suspend fun deleteTodo(todo: TodoDomain)



    fun getTodo(todoId: Int): Flow<TodoDomain>




}