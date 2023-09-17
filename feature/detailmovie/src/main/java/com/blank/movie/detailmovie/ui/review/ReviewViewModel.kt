package com.blank.movie.detailmovie.ui.review

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.blank.movie.domain.model.ReviewModel
import com.blank.movie.domain.usecase.GetReviewUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class ReviewViewModel @Inject constructor(
    getReviewUseCase: GetReviewUseCase
) : ViewModel() {
    private val _idMovie: MutableLiveData<Int> = MutableLiveData()

    val reviewsData: Flow<PagingData<ReviewModel>> =
        _idMovie.asFlow()
            .distinctUntilChanged()
            .flatMapLatest {
                getReviewUseCase.invoke(it)
            }
            .cachedIn(viewModelScope)

    fun setIdMovie(idMovie: Int) {
        _idMovie.value = idMovie
    }

}