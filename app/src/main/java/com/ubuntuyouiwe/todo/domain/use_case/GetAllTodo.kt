package com.ubuntuyouiwe.todo.domain.use_case

import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodo @Inject constructor(
    private val repository: TodoRepository
) {

    // todo filtreleme sıralama gibi işlemler yapılacak
    // todo paging3 eklenecek
    /*operator fun invoke(): Flow<List<TodoDomain>> {
        return repository.getAllTodo()
    }*/


}