package com.ubuntuyouiwe.todo.data.repository

import com.ubuntuyouiwe.todo.data.dto.local.toTodoDomain
import com.ubuntuyouiwe.todo.data.source.local.TodoDao
import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.model.local.toTodoDto
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {
    override suspend fun insertTodo(todo: TodoDomain) {
        todoDao.insertTodo(todo.toTodoDto())
    }

    override suspend fun deleteTodo(todo: TodoDomain) {
        todoDao.deleteTodo(todo.toTodoDto())
    }


    // todo paging3 eklenecek
    override fun getAllTodo(): Flow<List<TodoDomain>> {
        return todoDao.getAllTodo().map { todoList ->
            todoList.map { todoDto ->
                todoDto.toTodoDomain()
            }
        }
    }

    override fun getTodo(todoId: Int): Flow<TodoDomain> {
        return todoDao.getTodo(todoId).map {todo ->

            todo.toTodoDomain()


        }
    }
}