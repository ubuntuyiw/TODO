package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain

sealed class EditOrAddEvent {

    data class DeleteTodo(val id: Int): EditOrAddEvent()
    object SaveTodo: EditOrAddEvent()
    object UpdateTodo: EditOrAddEvent()




}
