package com.blank.movie.data

import com.blank.movie.data.model.ErrorResponse
import com.blank.movie.data.model.MoviesResponse
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.data.model.ResultMovieResponse
import com.blank.movie.data.model.ReviewResponse
import com.blank.movie.data.model.VideoResponse

interface MovieDataSource {

    suspend fun getMovieList(
        genreId: String,
        page: Int
    ): NetworkResponse<MoviesResponse, ErrorResponse>

    suspend fun getDetailMovie(idMovie: String): NetworkResponse<ResultMovieResponse, ErrorResponse>
    suspend fun getVideoData(idMovie: String): NetworkResponse<VideoResponse, ErrorResponse>

    suspend fun getReview(
        idMovie: String,
        page: Int
    ): NetworkResponse<ReviewResponse, ErrorResponse>
}