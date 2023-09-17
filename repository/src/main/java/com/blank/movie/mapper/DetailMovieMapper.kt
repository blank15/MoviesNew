package com.blank.movie.mapper

import com.blank.movie.data.model.detailmovie.DetailMovieResponse
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.helper.DomainMapper
import javax.inject.Inject

class DetailMovieMapper @Inject constructor() :
    DomainMapper<DetailMovieResponse, DetailMovieModel> {
    override fun mapToDomainModel(dataModel: DetailMovieResponse): DetailMovieModel {
        return with(dataModel) {
            DetailMovieModel(
                id = id ?: 0,
                originalName = originalTitle.orEmpty(),
                overview = overview.orEmpty(),
                popularity = popularity ?: 0.0,
                posterPath = posterPath.orEmpty(),
                backdropPath = backdropPath.orEmpty(),
                releaseDate = releaseDate.orEmpty(),
                title = title.orEmpty(),
                synopsis = overview.orEmpty(),
                voteAverage = voteAverage ?: 0.0,
                genre = genres.map {
                    it.name
                }.joinToString(",")
            )
        }
    }
}