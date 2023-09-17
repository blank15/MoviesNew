package com.blank.movie.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.blank.movie.data.MovieDataSource
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.data.model.ResultMovieResponse
import com.blank.movie.helper.convertToDomainError
import java.io.IOException

class MoviesPagingSourceHelper(
    private val movieDataSource: MovieDataSource
) : PagingSource<Int, ResultMovieResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ResultMovieResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovieResponse> {
        val pageIndex = params.key ?: STARTING_INDEX
        return try {
            val response = movieDataSource.getMovieList(pageIndex)
            when (response) {
                is NetworkResponse.Success -> {
                    val result = response.data.results
                    val endOfPagination = result.isEmpty()
                    val prevKey = if (pageIndex == 1) null else pageIndex - 1
                    val nextKey = if (endOfPagination) null else pageIndex + 1

                    LoadResult.Page(
                        data = result,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }

                is NetworkResponse.ErrorApi -> {
                    val error = response.convertToDomainError()
                    LoadResult.Error(Throwable(error.message))
                }

                is NetworkResponse.ErrorNetwork -> {
                    LoadResult.Error(response.error)
                }

                is NetworkResponse.ErrorUnknown -> {
                    LoadResult.Error(Throwable("Error Tidak di Ketahui"))
                }

                is NetworkResponse.SuccessNoData -> {
                    LoadResult.Page(emptyList(), null, null)
                }

            }
        } catch (error: IOException) {
            LoadResult.Error(error)
        }
    }

    companion object {
        const val STARTING_INDEX = 1
    }
}