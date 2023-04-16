package com.ubuntuyouiwe.todo.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

@OptIn(ExperimentalPagingApi::class)
class TodoListRemoteMediator constructor(
    private val query: Query
) : RemoteMediator<QuerySnapshot, TodoDomain>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<QuerySnapshot, TodoDomain>
    ): MediatorResult {
        return try {
            val currentPage: QuerySnapshot = when (loadType) {
                LoadType.REFRESH -> {
                    query.get(Source.SERVER).await()
                }
                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                    val lastVisibleTodo = state.pages.flatMap { it.data }.indexOf(lastItem)
                    query.startAfter(lastVisibleTodo).get(Source.SERVER).await()
                }
            }

            val endOfPaginationReached = (currentPage.size() ?: 0) < 20
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}