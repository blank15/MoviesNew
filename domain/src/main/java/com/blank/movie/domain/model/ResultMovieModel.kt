package com.blank.movie.domain.model


data class ResultMovieModel(
    val id: Int = 0,
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val title: String = ""

)