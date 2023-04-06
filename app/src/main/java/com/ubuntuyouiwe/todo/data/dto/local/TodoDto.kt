package com.ubuntuyouiwe.todo.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import java.sql.Timestamp

@Entity(tableName = "todo")
data class TodoDto(
    @PrimaryKey(autoGenerate = true)
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

fun TodoDto.toTodoDomain(): TodoDomain {
    return TodoDomain(
        id = id,
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
    RECURRING_MONTHLY
}
