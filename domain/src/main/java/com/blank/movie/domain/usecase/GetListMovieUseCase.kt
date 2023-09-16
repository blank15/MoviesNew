package com.blank.movie.domain.usecase

import androidx.paging.PagingData
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListMovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {

    operator fun invoke(): Flow<PagingData<ResultMovieModel>> =
        movieRepository.getMovieList()
}