package com.blank.movie.data.network

import com.blank.movie.data.MovieDataSource
import com.blank.movie.data.model.ErrorResponse
import com.blank.movie.data.model.MoviesResponse
import com.blank.movie.data.model.NetworkResponse
import com.blank.movie.data.model.ReviewResponse
import com.blank.movie.data.model.VideoResponse
import com.blank.movie.data.model.detailmovie.DetailMovieResponse
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieDataSource {
    override suspend fun getMovieList(
        page: Int
    ): NetworkResponse<MoviesResponse, ErrorResponse> {
        return movieApi.getListMovie(page)
    }

    override suspend fun getDetailMovie(idMovie: Int): NetworkResponse<DetailMovieResponse, ErrorResponse> =
        movieApi.getDetailMovie(idMovie)

    override suspend fun getVideoData(idMovie: String): NetworkResponse<VideoResponse, ErrorResponse> =
        movieApi.getVideo(idMovie)

    override suspend fun getReview(
        idMovie: String,
        page: Int
    ): NetworkResponse<ReviewResponse, ErrorResponse> =
        movieApi.getReview(idMovie, page)
}