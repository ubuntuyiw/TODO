package com.ubuntuyouiwe.todo.data.dto.remote

import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain

data class TodoDto(
    val uuID: String? = null,
    val title: String? = null,
    val content: String? = null,
    val deadline: Timestamp? = null,
    val notificationOption: NotificationOption? = null,
    val isDone: Boolean? = null,
    val isFavorite: Boolean? = null,
    val isPinned: Boolean? = null,
    val createdAt: Timestamp? = null,
    val updatedAt: Timestamp? = null
)


fun TodoDto.toTodoDomain(): TodoDomain {
    return TodoDomain(
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

enum class NotificationOption {
    ONE_HOUR_BEFORE,
    RECURRING_HOURLY,
    RECURRING_DAILY,
    RECURRING_WEEKLY,
    RECURRING_MONTHLY,
    NONE
}
