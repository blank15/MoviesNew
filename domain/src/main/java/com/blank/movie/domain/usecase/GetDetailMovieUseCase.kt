package com.blank.movie.domain.usecase

import com.blank.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetDetailMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    operator fun invoke(idMovie: Int) =
        repository.getDetailMovie(idMovie)
}