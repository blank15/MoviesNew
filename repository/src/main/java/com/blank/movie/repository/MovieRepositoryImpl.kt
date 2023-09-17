package com.blank.movie.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.blank.movie.data.MovieDataSource
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.helper.convertToDomainError
import com.blank.movie.helper.createPager
import com.blank.movie.helper.dataSourceHandling
import com.blank.movie.mapper.DetailMovieMapper
import com.blank.movie.mapper.MovieMapper
import com.blank.movie.mapper.ReviewMapper
import com.blank.movie.mapper.YoutubeVideoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mapperMovie: MovieMapper,
    private val movieDataSource: MovieDataSource,
) : MovieRepository {

    override fun getMovieList(
    ): Flow<PagingData<ResultMovieModel>> {
        return createPager(
            config = PagingConfig(
                pageSize = LIMIT_PAGING,
                initialLoadSize = LIMIT_PAGING
            )
        ) { page ->
            when (val response = movieDataSource.getMovieList(page)) {
                is NetworkResponse.Success -> {
                    Pair(null, response.data.results)
                }

                is NetworkResponse.ErrorApi -> {
                    val error = response.convertToDomainError()
                    Pair(Throwable(error.message), emptyList())
                }

                is NetworkResponse.ErrorNetwork -> {
                    Pair(response.error, emptyList())
                }

                is NetworkResponse.ErrorUnknown -> {
                    Pair(Throwable("Error Tidak di Ketahui"), emptyList())
                }

                is NetworkResponse.SuccessNoData -> {
                    Pair(null, emptyList())
                }

            }
        }.flow.map { pagingData ->
            pagingData.map(mapperMovie::mapToDomainModel)
        }
    }


    override fun getDetailMovie(idMovie: Int): Flow<DomainResource<DetailMovieModel>> {
        return dataSourceHandling(
            callApi = { movieDataSource.getDetailMovie(idMovie) },
            mapper = DetailMovieMapper()
        )
    }

    override fun getVideoData(idMovie: Int): Flow<DomainResource<List<String>>> {
        return dataSourceHandling(
            callApi = { movieDataSource.getVideoData(idMovie) },
            mapper = YoutubeVideoMapper()
        )
    }

    override fun getReview(idMovie: Int): Flow<PagingData<ReviewModel>> {
        return createPager(
            config = PagingConfig(
                pageSize = LIMIT_PAGING,
                initialLoadSize = LIMIT_PAGING
            )
        ) { page ->
            when (val response = movieDataSource.getReview(idMovie, page)) {
                is NetworkResponse.Success -> {
                    Pair(null, response.data.results)
                }

                is NetworkResponse.ErrorApi -> {
                    val error = response.convertToDomainError()
                    Pair(Throwable(error.message), emptyList())
                }

                is NetworkResponse.ErrorNetwork -> {
                    Pair(response.error, emptyList())
                }

                is NetworkResponse.ErrorUnknown -> {
                    Pair(Throwable("Error Tidak di Ketahui"), emptyList())
                }

                is NetworkResponse.SuccessNoData -> {
                    Pair(null, emptyList())
                }

            }
        }.flow.map { pagingData ->
            pagingData.map(ReviewMapper()::mapToDomainModel)
        }
    }

    companion object {
        private const val LIMIT_PAGING = 25 // not implement due the api request
    }
}