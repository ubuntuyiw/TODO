package com.ubuntuyouiwe.todo.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import kotlinx.coroutines.tasks.await

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
                    Log.v("RemoteMediator", "REFRESH")
                    query.get(Source.SERVER).await()
                }

                LoadType.PREPEND -> {
                    Log.v("RemoteMediator", "PREPEND")
                    return MediatorResult.Success(endOfPaginationReached = false)
                }

                LoadType.APPEND -> {
                    Log.v("RemoteMediator", "APPEND")
                    Log.v("RemoteMediator", state.lastItemOrNull().toString())

                    val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                    query.startAfter(lastItem.deadline).get(Source.SERVER).await()

                }
            }


            val endOfPaginationReached = currentPage.size() < state.config.pageSize

            Log.v(
                "RemoteMediator",
                endOfPaginationReached.toString() + " : " + state.config.pageSize + " : " + currentPage.size()
            )

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            Log.v("RemoteMediator", "ERROR")

            MediatorResult.Error(e)
        }
    }
}