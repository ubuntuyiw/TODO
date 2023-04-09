package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class GetTodo @Inject constructor(
    private val repository: TodoRepository
) {

    /*operator fun invoke(todoId: Int): Flow<TodoDomain> {
        return repository.getTodo(todoId)

    }*/

}