package com.blank.movie.mapper

import com.blank.movie.data.model.YoutubeVideoResponse
import com.blank.movie.helper.DomainMapper
import javax.inject.Inject

class YoutubeVideoMapper @Inject constructor() : DomainMapper<YoutubeVideoResponse, List<String>> {
    override fun mapToDomainModel(dataModel: YoutubeVideoResponse): List<String> {
        return dataModel.results.map {
            it.key.orEmpty()
        }
    }
}