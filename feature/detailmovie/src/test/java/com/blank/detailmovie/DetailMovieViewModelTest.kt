package com.blank.detailmovie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.blank.movie.detailmovie.ui.detail.DetailMovieViewModel
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.usecase.GetDetailMovieUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private var mockGetDetailMovieUseCase: GetDetailMovieUseCase = mockk()

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()


    val testDispatchers = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatchers)
        viewModel = DetailMovieViewModel(mockGetDetailMovieUseCase)
    }

    @Test
    fun `Get Detail movie from use case with give id movie should return success `() {
        val result = DomainResource.Success(DetailMovieModel())
        val mockkDetailMovieObserver =
            mockk<Observer<DomainResource<DetailMovieModel>>>(relaxed = true)
        coEvery {
            mockGetDetailMovieUseCase.invoke(1)
        } returns flowOf(result)
        viewModel.detailMovieData.observeForever(mockkDetailMovieObserver)
        viewModel.getDetailMovie(1)
        verify {
            mockkDetailMovieObserver.onChanged(result)
        }

    }
}