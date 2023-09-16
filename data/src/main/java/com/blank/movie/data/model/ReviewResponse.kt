package com.blank.movie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    var resultsMovie: List<ReviewListResponse>
)

@Serializable
data class ReviewListResponse(
    val id: String,
    val author: String,
    val content: String
)