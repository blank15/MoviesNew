package com.blank.movie.domain

import androidx.paging.PagingData
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.domain.repository.MovieRepository
import com.blank.movie.domain.usecase.GetReviewUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class GetReviewUseCaseUnitTest : BaseTest() {
    private lateinit var getReviewUseCase: GetReviewUseCase
    private val movieRepository: MovieRepository = mockk()

    override fun setUp() {
        super.setUp()
        getReviewUseCase = GetReviewUseCase(movieRepository)
    }

    @Test
    fun `Given data list review when call use case `() {
        val mockResult = listOf(ReviewModel())
        coEvery {
            movieRepository.getReview(1)
        } returns flowOf(PagingData.from(mockResult))

        val result = runBlocking {
            getReviewUseCase.invoke(1).first().toList()
        }

        assertEquals(mockResult, result)
    }
}

