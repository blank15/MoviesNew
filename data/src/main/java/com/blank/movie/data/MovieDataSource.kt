package com.blank.movie.data

import com.blank.movie.data.model.ErrorResponse
import com.blank.movie.data.model.MoviesResponse
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.data.model.ReviewResponse
import com.blank.movie.data.model.VideoResponse
import com.blank.movie.data.model.detailmovie.DetailMovieResponse

interface MovieDataSource {

    suspend fun getMovieList(
        page: Int
    ): NetworkResponse<MoviesResponse, ErrorResponse>

    suspend fun getDetailMovie(idMovie: Int): NetworkResponse<DetailMovieResponse, ErrorResponse>
    suspend fun getVideoData(idMovie: String): NetworkResponse<VideoResponse, ErrorResponse>

    suspend fun getReview(
        idMovie: String,
        page: Int
    ): NetworkResponse<ReviewResponse, ErrorResponse>
}