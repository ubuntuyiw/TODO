package com.ubuntuyouiwe.todo.data.repository

import android.util.Log
import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Source
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto
import com.ubuntuyouiwe.todo.data.dto.remote.toTodoDomain
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.tasks.await

class TodoListPagingSource constructor(
    private val query: Query
) : PagingSource<QuerySnapshot, TodoDomain>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, TodoDomain>): QuerySnapshot? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let {
                if (it.prevKey != null) {
                    it.prevKey
                } else if (it.nextKey != null) {
                    it.nextKey
                } else {
                    null
                }
            }
        }
    }


    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, TodoDomain> {
        return try {

            val currentPage = params.key ?: query.get(Source.CACHE).await()

            val lastVisibleTodo = currentPage.documents[currentPage.size() - 1].get("deadline") as Timestamp

            val nextPage = query.startAfter(lastVisibleTodo).get(Source.CACHE).await()

            Log.v("RemoteMediator", "PagingSource" + nextPage.isEmpty.toString())

            LoadResult.Page(
                data = currentPage.toObjects(TodoDto::class.java).map { it.toTodoDomain() },
                prevKey = null,
                nextKey = if (nextPage.isEmpty) null else nextPage
            )
        } catch (e: Exception) {
            Log.v("RemoteMediator", e.message.toString())
            LoadResult.Error(e)
        }
    }

}