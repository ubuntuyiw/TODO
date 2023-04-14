package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.model.remote.toHashMap
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class UpdateTodo @Inject constructor(
    private val repository: TodoRepository
) {


    operator fun invoke(todoUUID: String, todoDomain: TodoDomain) {
        repository.updateTodo(todoUUID,todoDomain.toHashMap())
    }


}