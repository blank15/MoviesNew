package com.blank.movie.domain.usecase

import com.blank.movie.domain.repository.MovieRepository
import javax.inject.Inject

class GetReviewUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(idMovie: Int) =
        movieRepository.getReview(idMovie)
}