package com.ubuntuyouiwe.todo.domain.use_case

class TodoUseCase(
    val getTodoList: GetTodoList,
    val getTodo: GetTodo,
    val deleteTodo: DeleteTodo,
    val insertTodo: InsertTodo,
    val getTodoCount: TodoCount,
    val updateTodo: UpdateTodo
)


