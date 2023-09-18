package com.blank.movie.domain

import androidx.paging.PagingData
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.domain.usecase.GetListMovieUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetMovieListUseCaseUnitTest : BaseTest() {
    private lateinit var getListMovieUseCase: GetListMovieUseCase
    private val movieRepository: MovieRepository = mockk()

    override fun setUp() {
        super.setUp()
        getListMovieUseCase = GetListMovieUseCase(movieRepository)
    }

    @Test
    fun `Given data list movie when call use case `() {
        val mockResult = listOf(ResultMovieModel())
        coEvery {
            movieRepository.getMovieList()
        } returns flowOf(PagingData.from(mockResult))

        val result = runBlocking {
            getListMovieUseCase.invoke().first().toList()
        }

        assertEquals(mockResult, result)
    }
}