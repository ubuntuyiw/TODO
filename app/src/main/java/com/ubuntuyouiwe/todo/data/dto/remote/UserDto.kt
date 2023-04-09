package com.ubuntuyouiwe.todo.data.dto.remote

data class UserDto(
    val uuid: String,
    val email: String,
    val profilePhoto: String,
    val name: String,
    val sureName: String
)