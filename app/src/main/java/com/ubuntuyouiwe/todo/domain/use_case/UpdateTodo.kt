package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class UpdateTodo @Inject constructor(
    private val repository: TodoRepository
) {


    /*suspend operator fun invoke(todo: TodoDomain){

        repository.updateTodo(todo)


    }*/

}