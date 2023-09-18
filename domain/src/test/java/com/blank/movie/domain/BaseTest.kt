package com.blank.movie.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
abstract class BaseTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    val testDispatchers = UnconfinedTestDispatcher()

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatchers)
    }

    @After
    open fun cleanUp() {
        Dispatchers.resetMain()
    }

    suspend fun <T : Any> PagingData<T>.toList(): List<T> {
        val dcb = object : DifferCallback {
            override fun onChanged(position: Int, count: Int) = Unit
            override fun onInserted(position: Int, count: Int) = Unit
            override fun onRemoved(position: Int, count: Int) = Unit
        }
        val items = mutableListOf<T>()
        val dif = object : PagingDataDiffer<T>(dcb, UnconfinedTestDispatcher()) {
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

