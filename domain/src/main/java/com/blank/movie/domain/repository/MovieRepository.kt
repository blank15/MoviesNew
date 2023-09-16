package com.blank.movie.domain.repository

import androidx.paging.PagingData
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.domain.model.VideoModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(
    ): Flow<PagingData<ResultMovieModel>>

    fun getDetailMovie(idMovie: String): Flow<DomainResource<ResultMovieModel>>
    fun getVideoData(idMovie: String): Flow<DomainResource<VideoModel>>

    fun getReview(
        idMovie: String,
        page: Int
    ): Flow<DomainResource<ReviewModel>>
}