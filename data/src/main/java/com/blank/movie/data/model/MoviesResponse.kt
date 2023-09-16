package com.blank.movie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null,
    var results: List<ResultMovieResponse>? = null
)