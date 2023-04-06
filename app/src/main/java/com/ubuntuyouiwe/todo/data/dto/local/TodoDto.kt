package com.ubuntuyouiwe.todo.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey
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

enum class NotificationOption {
    ONE_HOUR_BEFORE,
    RECURRING_HOURLY,
    RECURRING_DAILY,
    RECURRING_WEEKLY,
    RECURRING_MONTHLY
}
