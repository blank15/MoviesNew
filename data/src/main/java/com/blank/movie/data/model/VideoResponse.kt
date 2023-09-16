package com.blank.movie.data.model

import kotlinx.serialization.Serializable


@Serializable
data class VideoResponse(
    val id: Int,
    val results: List<HashMap<String, String>>
)