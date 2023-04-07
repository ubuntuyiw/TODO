package com.ubuntuyouiwe.todo.domain.model.local

import com.ubuntuyouiwe.todo.data.dto.local.NotificationOption
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto
import java.sql.Timestamp

data class TodoDomain(
    val id: Long? = null,
    val title: String,
    val content: String,
    val deadline: Timestamp,
    val notificationOption: NotificationOption,
    val isDone: Boolean,
    val isFavorite: Boolean,
    val isPinned: Boolean,
    val createdAt: Timestamp,
    val updatedAt: Timestamp
)


fun TodoDomain.toTodoDto(): TodoDto {
    return TodoDto(
        title = title,
        content = content,
        deadline = deadline,
        notificationOption = notificationOption,
        isDone = isDone,
        isFavorite = isFavorite,
        isPinned = isPinned,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

