package com.ubuntuyouiwe.todo.domain.model.remote

import com.google.firebase.Timestamp
import com.ubuntuyouiwe.todo.data.dto.remote.NotificationOption
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto

data class TodoDomain(
    val uuID: String? = null,
    val title: String?,
    val content: String?,
    val deadline: Timestamp?,
    val notificationOption: NotificationOption?,
    val isDone: Boolean?,
    val isFavorite: Boolean?,
    val isPinned: Boolean?,
    val createdAt: Timestamp?,
    val updatedAt: Timestamp?

)
class InvalidTodoException(val fieldType: InvalidTodoFieldType, message: String): Exception(message)
enum class InvalidTodoFieldType {
    TITLE,
    CONTENT,
    DEADLINE,
    NOTIFICATION_OPTION,
    IS_DONE,
    IS_FAVORITE,
    IS_PINNED,
    CREATED_AT,
    UPDATE_AT
}
fun TodoDomain.toHashMap(): HashMap<String,Any> {
    val map = HashMap<String,Any>()
    map["title"] = title!!
    map["content"] = content!!
    map["deadline"] = deadline!!
    map["notificationOption"] = notificationOption!!
    map["isDone"] = isDone!!
    map["isFavorite"] = isFavorite!!
    map["isPinned"] = isPinned!!
    map["createdAt"] = createdAt!!
    map["updatedAt"] = updatedAt!!
    return map
}
fun TodoDomain.toTodoDto(): TodoDto {
    return TodoDto(
        uuID = uuID,
        title = title,
        content =content,
        deadline = deadline,
        notificationOption = notificationOption,
        isDone = isDone,
        isFavorite = isFavorite,
        isPinned = isPinned,
        createdAt = createdAt,
        updatedAt = updatedAt

    )
}



