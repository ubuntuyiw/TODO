package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class InsertTodo @Inject constructor(
    private val repository: TodoRepository
){
    // todo gelen verilerin kontrolü yapılacak
    suspend operator fun invoke(todo: TodoDomain) {

        repository.insertTodo(todo)


    }











}