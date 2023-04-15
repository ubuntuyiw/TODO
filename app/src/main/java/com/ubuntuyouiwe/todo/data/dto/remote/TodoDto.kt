package com.ubuntuyouiwe.todo.data.dto.remote

import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain

data class TodoDto(
    val uuid: String? = null,
    val title: String? = null,
    val content: String? = null,
    val deadline: Timestamp? = null,
    val isDone: Boolean? = null,
    val isFavorite: Boolean? = null,
    val isPinned: Boolean? = null,
    val createdAt: Timestamp? = null,
)


fun TodoDto.toTodoDomain(): TodoDomain {
    return TodoDomain(
        uuid = uuid,
        title = title,
        content = content,
        deadline = deadline,
        isDone = isDone,
        isFavorite = isFavorite,
        isPinned = isPinned,
        createdAt = createdAt,
    )
}

enum class NotificationOption {
    EVERY_DAY,
    EVERY_WEEK,
    MONTHLY,
    EVERY_YEAR,
    NONE
}
