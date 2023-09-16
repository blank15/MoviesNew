package com.blank.movie.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    var statusMessage: String?,
    var statusCode: Int? = null,
)

