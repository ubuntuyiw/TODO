package com.ubuntuyouiwe.todo.domain.model.local

import com.ubuntuyouiwe.todo.data.dto.local.NotificationOption
import com.ubuntuyouiwe.todo.data.dto.local.Response
import com.ubuntuyouiwe.todo.data.dto.local.TodoDto
import java.sql.Timestamp

data class TodoDomain(
    val id: Long? = null,
    val response: List<Response>,
    val page: Int,
    val totalPage: Int,
    val totalResponse: Int

)



fun TodoDomain.toTodoDto(): TodoDto {
    return TodoDto(
        response = response,
        page = page,
        totalPage = totalPage,
        totalResponse = totalResponse
    )
}