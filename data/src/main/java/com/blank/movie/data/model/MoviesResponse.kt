package com.blank.movie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    var resultsMovie: List<ResultMovieResponse>
)