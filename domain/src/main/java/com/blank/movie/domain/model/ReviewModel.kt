package com.blank.movie.domain.model

data class ReviewModel(
    val id: String = "",
    val urlImage: String = "",
    val authorName: String = "",
    val review: String = "",
    val rating: Int = 0
)