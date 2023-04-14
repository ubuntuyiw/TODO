package com.ubuntuyouiwe.todo.domain.use_case

import android.util.Log
import com.ubuntuyouiwe.todo.domain.model.remote.InvalidTodoException
import com.ubuntuyouiwe.todo.domain.model.remote.InvalidTodoFieldType
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.model.remote.toHashMap
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.util.Resource
import javax.inject.Inject

class InsertTodo @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(todo: TodoDomain) {
        isTodoValid(todo)

        repository.add(todo.toHashMap())


    }

    @Throws(InvalidTodoException::class)
    private fun isTodoValid(todo: TodoDomain) {
        if (todo.title == null)
            throw InvalidTodoException(InvalidTodoFieldType.TITLE, "title null")

        if (todo.content == null)
            throw InvalidTodoException(InvalidTodoFieldType.CONTENT, "content null")

        if (todo.deadline == null)
            throw InvalidTodoException(InvalidTodoFieldType.DEADLINE, "deadline null")


        if (todo.isDone == null)
            throw InvalidTodoException(InvalidTodoFieldType.IS_DONE, "isDone null")

        if (todo.isFavorite == null)
            throw InvalidTodoException(InvalidTodoFieldType.IS_FAVORITE, "isFavorite null")

        if (todo.isPinned == null)
            throw InvalidTodoException(InvalidTodoFieldType.IS_PINNED, "isPinned null")

        if (todo.createdAt == null)
            throw InvalidTodoException(InvalidTodoFieldType.CREATED_AT, "createdAt null")



    }


}