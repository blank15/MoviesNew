package com.blank.movie.domain

import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.domain.usecase.GetDetailMovieUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetDetailMovieUseCaseUnitTest : BaseTest() {

    private lateinit var getDetailMovieUseCase: GetDetailMovieUseCase
    private val movieRepository: MovieRepository = mockk()

    override fun setUp() {
        super.setUp()
        getDetailMovieUseCase = GetDetailMovieUseCase(movieRepository)
    }

    @Test
    fun `Given data detail movie and video when call use case then should return data combine two data`() {
        val resultDetailMovie = DomainResource.Success(DetailMovieModel())
        val resultVideoData = DomainResource.Success(listOf("12", "21"))
        val newResultDetailMovie =
            DomainResource.Success(DetailMovieModel(videosId = listOf("12", "21")))
        coEvery {
            movieRepository.getDetailMovie(1)
        } returns flowOf(resultDetailMovie)
        coEvery {
            movieRepository.getVideoData(1)
        } returns flowOf(resultVideoData)
        val result = runBlocking {
            getDetailMovieUseCase.invoke(1).first()
        }
        assertEquals(newResultDetailMovie, result)
    }
}