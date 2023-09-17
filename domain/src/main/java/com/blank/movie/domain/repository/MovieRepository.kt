package com.blank.movie.domain.repository

import androidx.paging.PagingData
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.model.ReviewModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovieList(
    ): Flow<PagingData<ResultMovieModel>>

    fun getDetailMovie(idMovie: Int): Flow<DomainResource<DetailMovieModel>>
    fun getVideoData(idMovie: Int): Flow<DomainResource<List<String>>>
    fun getReview(
        idMovie: Int
    ): Flow<PagingData<ReviewModel>>
}