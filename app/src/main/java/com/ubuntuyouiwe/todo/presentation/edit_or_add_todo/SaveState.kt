package com.ubuntuyouiwe.todo.presentation.edit_or_add_todo

sealed class SaveState {
    data class Error(val message: String): SaveState()
    object Success: SaveState()
}
