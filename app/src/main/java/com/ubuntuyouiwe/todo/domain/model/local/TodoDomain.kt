package com.ubuntuyouiwe.todo.domain.model.local

import com.ubuntuyouiwe.todo.data.dto.local.NotificationOption
import java.sql.Timestamp

data class TodoDomain(
    val id: Long,
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
