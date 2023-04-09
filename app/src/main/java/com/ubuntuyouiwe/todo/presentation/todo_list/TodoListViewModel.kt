package com.ubuntuyouiwe.todo.presentation.todo_list

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val useCase: TodoUseCase
) : ViewModel() {


    var state = MutableStateFlow<PagingData<TodoDomain>>(PagingData.empty())
        private set

    init {
        showList()

    }

    //val st = useCase.getTodoList().launchIn(viewModelScope)



    fun showList() {
        /*useCase.getTodoList().onEach {
            state.value = it
        }.launchIn(viewModelScope)*/

       useCase.getTodoList().cachedIn(viewModelScope)
            .onEach { state.value = it }
            .launchIn(viewModelScope)


    }


}