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
): ViewModel(){



    var title = MutableStateFlow<String?>(null)
        private set
    var content = MutableStateFlow<String?>(null)
        private set
    var deadline = MutableStateFlow<String?>(null)
        private set
    var notificationOption = MutableStateFlow<NotificationOption?>(null)
        private set
    var isDone = MutableStateFlow<Boolean?>(null)
        private set
    var isFavorite = MutableStateFlow<Boolean?>(null)
        private set

    var isPinned = MutableStateFlow<Boolean?>(null)
        private set

    var createdAt = MutableStateFlow<Timestamp?>(null)
        private set

    var updatedAt = MutableStateFlow<Timestamp?>(null)
        private set

    var saveState = MutableStateFlow<SaveState?>(null).value
        private set

    fun saveNote() {

        viewModelScope.launch {

            saveState = try {
                todoUseCase.insertTodo(TodoDomain(
                    title = "Do the laundry",
                    content = "Separate colors and whites, then wash on gentle cycle",
                    deadline = Timestamp.now(),
                    notificationOption = NotificationOption.NONE,
                    isDone = false,
                    isFavorite = true,
                    isPinned = true,
                    createdAt = Timestamp.now(),
                    updatedAt = Timestamp.now()))
                SaveState.Success

            }catch (e: Exception) {
                SaveState.Error(e.message.toString())
            }

        }




    }






}