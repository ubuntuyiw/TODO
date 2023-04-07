package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class DeleteTodo @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(todo: TodoDomain) {

        repository.deleteTodo(todo)

    }



}