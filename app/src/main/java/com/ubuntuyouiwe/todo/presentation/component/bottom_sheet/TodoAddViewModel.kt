package com.ubuntuyouiwe.todo.presentation.component.bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import com.ubuntuyouiwe.todo.presentation.edit_or_add_todo.SaveState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoAddViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
): ViewModel() {

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
                todoUseCase.insertTodo(
                    TodoDomain(
                        title = "Do the laundry",
                        content = "Separate colors and whites, then wash on gentle cycle",
                        deadline = Timestamp.now(),
                        notificationOption = NotificationOption.NONE,
                        isDone = false,
                        isFavorite = false,
                        isPinned = false,
                        createdAt = Timestamp.now(),
                        updatedAt = Timestamp.now()
                    )
                )
                SaveState.Success

            } catch (e: Exception) {
                SaveState.Error(e.message.toString())
            }

        }


    }
}