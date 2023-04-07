package com.ubuntuyouiwe.todo.di

import android.content.Context
import androidx.room.Room
import com.ubuntuyouiwe.todo.data.repository.TodoRepositoryImpl
import com.ubuntuyouiwe.todo.data.source.local.TodoDao
import com.ubuntuyouiwe.todo.data.source.local.TodoDatabase
import com.ubuntuyouiwe.todo.data.source.local.TodoDatabase.Companion.DATABASE_NAME
import com.ubuntuyouiwe.todo.domain.repositroy.TodoRepository
import com.ubuntuyouiwe.todo.domain.use_case.DeleteTodo
import com.ubuntuyouiwe.todo.domain.use_case.GetAllTodo
import com.ubuntuyouiwe.todo.domain.use_case.GetTodo
import com.ubuntuyouiwe.todo.domain.use_case.InsertTodo
import com.ubuntuyouiwe.todo.domain.use_case.TodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(
        @ApplicationContext context: Context
    ): TodoDatabase  = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Singleton
    @Provides
    fun provideInjectDao(database: TodoDatabase) = database.todoDao()

    @Singleton
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository =
        TodoRepositoryImpl(todoDao)

    @Singleton
    @Provides
    fun provideTodoUseCase(repository: TodoRepository) : TodoUseCase =
        TodoUseCase(
            getAllTodo = GetAllTodo(repository),
            deleteTodo = DeleteTodo(repository),
            getTodo = GetTodo(repository),
            insertTodo = InsertTodo(repository),

        )





}