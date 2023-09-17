package com.blank.movie.data.model

import kotlinx.serialization.SerialName

data class ErrorResponse(
    @SerialName("status_message")
    var statusMessage: String?,
    @SerialName("status_code")
    var statusCode: Int? = null,
)

