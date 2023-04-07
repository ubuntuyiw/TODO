package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTodo @Inject constructor(
    private val repository: TodoRepository
) {

    operator fun invoke(todoId: Int): Flow<TodoDomain> {
        return repository.getTodo(todoId)

    }

}