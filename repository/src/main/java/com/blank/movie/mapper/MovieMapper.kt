package com.blank.movie.mapper

import com.blank.movie.data.model.ResultMovieResponse
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.helper.DomainMapper
import javax.inject.Inject

class MovieMapper @Inject constructor() : DomainMapper<ResultMovieResponse, ResultMovieModel> {
    override fun mapToDomainModel(dataModel: ResultMovieResponse): ResultMovieModel {
        return with(dataModel) {
            ResultMovieModel(
                id = id ?: 0,
                originalName = original_title.orEmpty(),
                overview = overview.orEmpty(),
                popularity = popularity ?: 0.0,
                posterPath = poster_path.orEmpty(),
                backdropPath = backdropPath.orEmpty(),
                releaseDate = release_date.orEmpty(),
                title = title.orEmpty()
            )
        }
    }
}