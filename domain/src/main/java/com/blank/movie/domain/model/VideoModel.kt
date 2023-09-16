package com.blank.movie.domain.model


data class VideoModel(
    val id: Int,
    val results: List<HashMap<String, String>>
)