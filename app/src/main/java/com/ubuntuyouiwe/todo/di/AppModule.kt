package com.ubuntuyouiwe.todo.di

import android.content.Context
import androidx.room.Room
import com.ubuntuyouiwe.todo.data.source.local.TodoDatabase
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
    ) = Room.databaseBuilder(
        context,
        TodoDatabase::class.java,
        "TodoDB"
    ).build()


    @Singleton
    @Provides
    fun provideInjectDao(database: TodoDatabase) = database.todoDao()





}