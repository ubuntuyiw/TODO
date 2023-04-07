package com.ubuntuyouiwe.todo.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ubuntuyouiwe.todo.data.source.local.TodoDao
import com.ubuntuyouiwe.todo.domain.model.local.TodoDomain

class TodoListPagingSource constructor(
    val todoDao: TodoDao
): PagingSource<Int,TodoDomain>() {
    override fun getRefreshKey(state: PagingState<Int, TodoDomain>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TodoDomain> {
        TODO("Not yet implemented")
    }

}