package com.ubuntuyouiwe.todo.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.storage.FirebaseStorage
import com.ubuntuyouiwe.todo.data.dto.remote.Login
import com.ubuntuyouiwe.todo.data.dto.remote.SignUp
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto
import com.ubuntuyouiwe.todo.data.dto.remote.TodoPreview
import com.ubuntuyouiwe.todo.data.dto.remote.UserDto
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val store: FirebaseFirestore,
    private val storage: FirebaseStorage,
) : TodoRepository {


    init {
        val settings = firestoreSettings {
            isPersistenceEnabled = true
        }
        store.firestoreSettings = settings
    }

    override fun singUp(signUp: SignUp): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun login(login: Login): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override fun add(hashMap: HashMap<String, Any>): Resource<Boolean> {
        Log.v("hata hata", "1")

        val resource = store.collection("Todo").add(hashMap)
        Log.v("hata hata", "2")
        return if (resource.isSuccessful) {
            Log.v("hata hata", "resource.exception?.message.toString()")
            Resource.Success(true)


        } else {
            Log.v("hata hata", resource.exception?.message.toString())
            Resource.Error(resource.exception?.message.toString())

        }
    }
    private fun pagingSourceFactory(): () ->TodoListPagingSource {
        return {TodoListPagingSource(store.collection("Todo"))}
    }
    override fun getTodoList(): Flow<PagingData<TodoDomain>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory()
        ).flow
    }

    override suspend fun getTodo(query: Query): Flow<Resource<TodoDto>> {
        TODO("Not yet implemented")
    }

    override suspend fun getOtherUsers(query: Query): Flow<Resource<List<UserDto>>> {
        TODO("Not yet implemented")
    }


}