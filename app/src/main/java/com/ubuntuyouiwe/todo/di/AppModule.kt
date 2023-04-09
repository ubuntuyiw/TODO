package com.ubuntuyouiwe.todo.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import com.ubuntuyouiwe.todo.data.repository.TodoListPagingSource
import com.ubuntuyouiwe.todo.data.repository.TodoRepositoryImpl
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.domain.use_case.DeleteTodo
import com.ubuntuyouiwe.todo.domain.use_case.GetTodoList
import com.ubuntuyouiwe.todo.domain.use_case.GetTodo
import com.ubuntuyouiwe.todo.domain.use_case.InsertTodo
import com.ubuntuyouiwe.todo.domain.use_case.TodoCount
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import com.ubuntuyouiwe.todo.domain.use_case.UpdateTodo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {




    @Singleton
    @Provides
    fun provideTodoRepository(
        auth: FirebaseAuth,
        store: FirebaseFirestore,
        storage: FirebaseStorage,
    ): TodoRepository =
        TodoRepositoryImpl(auth,store,storage)



    @Singleton
    @Provides
    fun provideTodoUseCase(repository: TodoRepository) : TodoUseCase =
        TodoUseCase(
            getTodoList = GetTodoList(repository),
            deleteTodo = DeleteTodo(repository),
            getTodo = GetTodo(repository),
            insertTodo = InsertTodo(repository),
            getTodoCount = TodoCount(repository),
            updateTodo = UpdateTodo(repository)

        )





}