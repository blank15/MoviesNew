package com.blank.movie.data.network

import com.blank.movie.data.model.ErrorResponse
import com.blank.movie.data.model.MoviesResponse
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.data.model.ReviewResponse
import com.blank.movie.data.model.VideoResponse
import com.blank.movie.data.model.detailmovie.DetailMovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?language=en-US")
    suspend fun getListMovie(
        @Query("page") page: Int
    ): NetworkResponse<MoviesResponse, ErrorResponse>

    @GET("movie/{id}?language=en-US")
    suspend fun getDetailMovie(@Path("id") id: Int): NetworkResponse<DetailMovieResponse, ErrorResponse>

    @GET("movie/{id}/videos?language=en-US")
    suspend fun getVideo(@Path("id") idMovie: String): NetworkResponse<VideoResponse, ErrorResponse>

    @GET("movie/{id}/reviews?language=en-US")
    suspend fun getReview(
        @Path("id") id: String,
        @Query("page") page: Int
    ): NetworkResponse<ReviewResponse, ErrorResponse>
}