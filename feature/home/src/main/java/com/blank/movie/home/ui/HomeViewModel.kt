package com.blank.movie.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.blank.movie.domain.model.ResultMovieModel
import com.blank.movie.domain.usecase.GetListMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getListMovieUseCase: GetListMovieUseCase
) : ViewModel() {


    val moviesData: Flow<PagingData<ResultMovieModel>> =
        getListMovieUseCase()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

}