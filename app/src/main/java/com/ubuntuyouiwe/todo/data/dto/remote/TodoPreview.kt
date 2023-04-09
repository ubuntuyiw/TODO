package com.ubuntuyouiwe.todo.data.dto.remote

import com.google.firebase.Timestamp

data class TodoPreview(
    val id: String,
    val title: String,
    val deadline: Timestamp,
    val isDone: Boolean
)