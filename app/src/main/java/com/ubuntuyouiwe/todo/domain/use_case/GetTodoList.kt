package com.ubuntuyouiwe.todo.domain.use_case

import androidx.paging.PagingData
import com.google.firebase.firestore.Query
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTodoList @Inject constructor(
    private val repository: TodoRepository
) {


    operator fun invoke(): Flow<PagingData<TodoDomain>> = flow {
        repository.getTodoList().collect{
            emit(it)
        }
    }


}