package com.blank.movie.domain.model


data class ResultMovieModel(
    val id: Int,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val backdropPath: String,
    val releaseDate: String,
    val title: String

)