package com.example.fourchartest.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fourchartest.domain.model.MovieDetailInfo
import com.example.fourchartest.domain.useCases.GetDetailInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getDetailInfoUseCase: GetDetailInfoUseCase,
) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetailInfo>()
    val movie: LiveData<MovieDetailInfo>
        get() = _movie

    fun getDetailsInfo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieDetail = getDetailInfoUseCase(id)
            withContext((Dispatchers.Main)) {
                _movie.value = movieDetail
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}