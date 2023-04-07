package com.ubuntuyouiwe.todo.domain.use_case

class TodoUseCase(
    val getAllTodo: GetAllTodo,
    val getTodo: GetTodo,
    val deleteTodo: DeleteTodo,
    val insertTodo: InsertTodo
)