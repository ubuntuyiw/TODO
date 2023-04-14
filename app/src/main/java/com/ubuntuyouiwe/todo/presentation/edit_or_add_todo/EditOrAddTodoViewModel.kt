package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class EditOrAddTodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var deadline = MutableStateFlow<Timestamp?>(null)
        private set

    var title = MutableStateFlow<String?>(null)
        private set
    var content = MutableStateFlow<String?>(null)
        private set

    init {
        savedStateHandle.get<String>("title")?.let {
                title.value = it
        }
        savedStateHandle.get<String>("content")?.let {
            content.value = it
        }
        savedStateHandle.get<Timestamp>("deadline")?.let {

            deadline.value = it
        }
    }



    fun event(event: EditOrAddEvent) {

        when (event) {
            is EditOrAddEvent.SaveTodo -> {

                todoUseCase.insertTodo(TodoDomain(
                    title = title.value,
                    content = content.value,
                    deadline = deadline.value,
                    createdAt = Timestamp.now(),
                    isDone = false,
                    isFavorite = false,
                    isPinned = false,
                ))

            }
            is EditOrAddEvent.DeleteTodo -> {


            }
            is EditOrAddEvent.UpdateTodo -> {



            }
        }



    }








}