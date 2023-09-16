package com.blank.movie.data.model

import kotlinx.serialization.Serializable


@Serializable
data class ResultMovieResponse(
    val id: Int,
    val originalName: String?,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String?,
    val title: String?

)