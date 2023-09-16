package com.blank.movie.domain.model

data class MoviesModel(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    var resultsMovie: List<ResultMovieModel>
)