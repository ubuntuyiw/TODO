package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import javax.inject.Inject

class GetTodo @Inject constructor(
    private val repository: TodoRepository
) {


}