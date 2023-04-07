package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubuntuyouiwe.todo.data.dto.local.NotificationOption
import com.ubuntuyouiwe.todo.data.dto.local.Response
import com.ubuntuyouiwe.todo.data.source.local.SerializableTimestamp
import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.sql.Timestamp
import javax.inject.Inject

@HiltViewModel
class EditOrAddTodoViewModel @Inject constructor(
    private val todoUseCase: TodoUseCase
): ViewModel(){
    fun saveNote() {

        viewModelScope.launch {
            todoUseCase.insertTodo(
                TodoDomain(
                    response = listOf(
                        Response(
                            title = "Alışveriş yap",
                            content = "Migros'tan süt, ekmek ve yumurta al",
                            deadline = SerializableTimestamp(Timestamp.valueOf("2023-04-10 14:30:00")),
                            notificationOption = NotificationOption.NONE,
                            isDone = false,
                            isFavorite = true,
                            isPinned = false,
                            createdAt = SerializableTimestamp(Timestamp.valueOf("2023-04-10 14:30:00")),
                            updatedAt = SerializableTimestamp(Timestamp.valueOf("2023-04-10 14:30:00"))
                        )
                    ),
                    page = 1,
                    totalPage = 1,
                    totalResponse = 1

                )

            )
        }




    }






}