package com.blank.movie.domain.model


data class ReviewModel(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    var resultsMovie: List<ReviewListModel>
)


data class ReviewListModel(
    val id: String,
    val author: String,
    val content: String
)