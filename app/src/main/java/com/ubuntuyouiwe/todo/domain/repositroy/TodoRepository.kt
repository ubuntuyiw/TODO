package com.ubuntuyouiwe.todo.domain.repositroy

import androidx.paging.PagingData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.Query
import com.ubuntuyouiwe.todo.data.dto.remote.Login
import com.ubuntuyouiwe.todo.data.dto.remote.SignUp
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto
import com.ubuntuyouiwe.todo.data.dto.remote.UserDto
import com.ubuntuyouiwe.todo.data.repository.TodoListPagingSource
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun singUp(signUp: SignUp): Flow<Resource<Boolean>>

    fun login(login: Login): Flow<Resource<Boolean>>

    fun getCurrentUser(): FirebaseUser?

    fun add(hashMap: HashMap<String, Any>): Resource<Boolean>

   fun getTodoList(): Flow<PagingData<TodoDomain>>


    suspend fun getTodo(query: Query): Flow<Resource<TodoDto>>


    suspend fun getOtherUsers(query: Query): Flow<Resource<List<UserDto>>>

    fun updateTodo(todoUUID: String, hashMap: HashMap<String, Any>): Flow<Resource<Boolean>>


}