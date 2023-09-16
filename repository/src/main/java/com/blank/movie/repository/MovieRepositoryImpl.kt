package com.blank.movie.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.blank.movie.data.MovieDataSource
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.domain.model.VideoModel
import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.helper.PagerProviderHelper
import com.blank.movie.mapper.MovieMapper
import com.blank.movie.paging.MoviesPagingSourceHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val pagerProviderHelper: PagerProviderHelper,
    private val mapper: MovieMapper,
    private val movieDataSource: MovieDataSource,
) : MovieRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getMovieList(
    ): Flow<PagingData<ResultMovieModel>> {

        return pagerProviderHelper.createPager(
            config = PagingConfig(
                pageSize = LIMIT_PAGING,
                initialLoadSize = LIMIT_PAGING
            ),
            pagingSourceFactory = {
                MoviesPagingSourceHelper(movieDataSource)
            }
        ).flow.map { pagingData ->
            pagingData.map(mapper::mapToDomainModel)
        }
    }


    override fun getDetailMovie(idMovie: String): Flow<DomainResource<ResultMovieModel>> {
        TODO("Not yet implemented")
    }

    override fun getVideoData(idMovie: String): Flow<DomainResource<VideoModel>> {
        TODO("Not yet implemented")
    }

    override fun getReview(idMovie: String, page: Int): Flow<DomainResource<ReviewModel>> {
        TODO("Not yet implemented")
    }

    companion object {
        private const val LIMIT_PAGING = 25 // not implement due the api request
    }
}