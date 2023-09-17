package com.blank.movie.home

import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.usecase.GetListMovieUseCase
import com.blank.movie.home.ui.HomeViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class HomeViewModelTest {
    private lateinit var viewModelTest: HomeViewModel
    private val mockGetListMovieUseCase: GetListMovieUseCase = mockk()

    @Before
    fun setup() {
        viewModelTest = HomeViewModel(mockGetListMovieUseCase)
    }

    @Test
    fun `Given Mock data from usecase when fetch list movie then result should be to flow`() {
        val slotPagingData = slot<PagingData<ResultMovieModel>>()
        val mockMoviesObserver = mockk<Observer<PagingData<ResultMovieModel>>>(relaxed = true)
        val mockResult = listOf(ResultMovieModel())
        coEvery {
            mockGetListMovieUseCase.invoke()
        } returns
                flowOf(
                    PagingData.from(
                        mockResult
                    )
                )
        viewModelTest.moviesData.asLiveData().observeForever(mockMoviesObserver)

        verify {
            mockMoviesObserver.onChanged(capture(slotPagingData))
        }
        val resultUI = runBlocking { slotPagingData.captured.collectDataForTest() }

        assertEquals(mockResult, resultUI)
    }

    private suspend fun <T : Any> PagingData<T>.collectDataForTest(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) = Unit
            override fun onInserted(position: Int, count: Int) = Unit
            override fun onRemoved(position: Int, count: Int) = Unit
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, Dispatchers.Default) {
            override suspend fun presentNewList(
                previousList: NullPaddedList<T>,
                newList: NullPaddedList<T>,
                lastAccessedIndex: Int,
                onListPresentable: () -> Unit
            ): Int? {
                for (idx in 0 until newList.size)
                    items.add(newList.getFromStorage(idx))
                onListPresentable()
                return null
            }
        }
        dif.collectFrom(this)
        return items
    }
}

