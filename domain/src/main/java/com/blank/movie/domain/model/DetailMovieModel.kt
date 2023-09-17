package com.blank.movie.domain.model

data class DetailMovieModel(
    val id: Int = 0,
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val posterPath: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val synopsis: String = "",
    val voteAverage: Double = 0.0,
    val genre: String = "",
    var videosId: List<String> = listOf()

)