package com.blank.movie.domain.usecase

import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(idMovie: Int) =
        repository.getDetailMovie(idMovie)
            .mapToDataDetailWithVideo(repository.getVideoData(idMovie = idMovie))
}

private fun Flow<DomainResource<DetailMovieModel>>.mapToDataDetailWithVideo(listVideo: Flow<DomainResource<List<String>>>): Flow<DomainResource<DetailMovieModel>> =
    combine(listVideo) { dataDetailMovie, videosId ->

        when (dataDetailMovie) {
            is DomainResource.Error -> DomainResource.Error(
                dataDetailMovie.error,
                dataDetailMovie.message
            )

            DomainResource.Loading -> DomainResource.Loading
            is DomainResource.Success -> {
                val newData = dataDetailMovie.data
                when (videosId) {
                    is DomainResource.Error -> DomainResource.Error(
                        videosId.error,
                        videosId.message
                    )

                    DomainResource.Loading -> DomainResource.Loading
                    is DomainResource.Success -> {
                        newData.videosId = videosId.data
                        DomainResource.Success(newData)
                    }

                    is DomainResource.SuccessNoData -> DomainResource.SuccessNoData(videosId.message)
                }
            }

            is DomainResource.SuccessNoData -> DomainResource.SuccessNoData(dataDetailMovie.message)
        }
    }