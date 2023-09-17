package com.blank.movie.helper

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.io.IOException

class PagingSourceHelper<T : Any>(
    private val resourceResult: suspend (Int) -> Pair<Throwable?, List<T>>
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val pageIndex = params.key ?: STARTING_INDEX
        return try {

            val data = resourceResult(pageIndex)
            val result = data.second
            val endOfPagination = result.isEmpty()
            val prevKey = if (pageIndex == 1) null else pageIndex - 1
            val nextKey = if (endOfPagination) null else pageIndex + 1

            data.first?.let {
                LoadResult.Error(it)
            } ?: LoadResult.Page(
                data = result,
                prevKey = prevKey,
                nextKey = nextKey
            )

        } catch (error: IOException) {
            LoadResult.Error(error)
        }
    }

    companion object {
        const val STARTING_INDEX = 1
    }


}