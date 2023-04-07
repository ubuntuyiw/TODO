package com.ubuntuyouiwe.todo.data.dto.local

import androidx.room.Entity
import com.ubuntuyouiwe.todo.data.source.local.SerializableTimestamp
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
@Entity(tableName = "response")
data class Response(
    val title: String,
    val content: String,
    @Contextual
    val deadline: SerializableTimestamp,
    val notificationOption: NotificationOption,
    val isDone: Boolean,
    val isFavorite: Boolean,
    val isPinned: Boolean,
    @Contextual
    val createdAt: SerializableTimestamp,
    @Contextual
    val updatedAt: SerializableTimestamp
)
