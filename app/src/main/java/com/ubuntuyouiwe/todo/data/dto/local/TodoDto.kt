package com.ubuntuyouiwe.todo.data.dto.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "todo")
data class TodoDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val response: List<Response>,
    val page: Int,
    val totalPage: Int,
    val totalResponse: Int
)

fun TodoDto.toTodoDomain(): TodoDomain {
    return TodoDomain(
        response = response.map { response->
            Response(
                title = response.title,
                content = response.content,
                deadline = response.deadline,
                notificationOption = response.notificationOption,
                isDone = response.isDone,
                isFavorite = response.isFavorite,
                isPinned = response.isPinned,
                createdAt = response.createdAt,
                updatedAt = response.updatedAt
            )
        }
            ,
        id = id,
        page = page,
        totalResponse = totalResponse,
        totalPage = totalPage

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
