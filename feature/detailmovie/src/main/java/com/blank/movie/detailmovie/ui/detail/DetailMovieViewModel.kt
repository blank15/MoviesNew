package com.blank.movie.detailmovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blank.movie.domain.model.DetailMovieModel
import com.blank.movie.domain.model.DomainResource
import com.blank.movie.domain.usecase.GetDetailMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val getDetailMovieUseCase: GetDetailMovieUseCase
) : ViewModel() {

    private val _detailMovieData: MutableLiveData<DomainResource<DetailMovieModel>> =
        MutableLiveData()
    val detailMovieData: LiveData<DomainResource<DetailMovieModel>> get() = _detailMovieData

    fun getDetailMovie(idMovie: Int) {
        viewModelScope.launch {
            getDetailMovieUseCase(idMovie)
                .collect {
                    _detailMovieData.value = it
                }

        }
    }
}