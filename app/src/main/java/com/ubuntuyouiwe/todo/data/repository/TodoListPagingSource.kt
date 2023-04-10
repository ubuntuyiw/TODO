package com.ubuntuyouiwe.todo.data.repository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.ubuntuyouiwe.todo.data.dto.remote.TodoDto
import com.ubuntuyouiwe.todo.data.dto.remote.toTodoDomain
import com.ubuntuyouiwe.todo.domain.model.remote.TodoDomain
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class TodoListPagingSource constructor(
    private val query: Query
) : PagingSource<QuerySnapshot, TodoDomain>() {
    override fun getRefreshKey(state: PagingState<QuerySnapshot, TodoDomain>): QuerySnapshot? {

        /*return state.lastItemOrNull()?.let { item ->
            state.pages.firstOrNull { page ->
                page.data.contains(item)
            }?.prevKey
        }*/

        /*return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.let { anchorPage ->
                anchorPage.nextKey ?: anchorPage.prevKey
            }
        }*/

        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, TodoDomain> {

        return try {

            val currentPage = params.key ?: query.get().await()

            val lastVisibleTodo = currentPage.documents[currentPage.size() - 1]
            val nextPage = query.startAfter(lastVisibleTodo).get().await()


            LoadResult.Page(
                data = currentPage.toObjects(TodoDto::class.java).map { it.toTodoDomain() },
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }

}