package com.blank.movie.data.model

import kotlinx.serialization.Serializable


@Serializable
data class ResultMovieResponse(
    val id: Int? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val backdropPath: String? = null,
    val release_date: String? = null,
    val title: String? = null

)