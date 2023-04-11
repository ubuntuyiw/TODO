package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditOrAddTodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
) : ViewModel() {





}