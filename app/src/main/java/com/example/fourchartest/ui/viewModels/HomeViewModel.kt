package com.example.fourchartest.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fourchartest.domain.model.MovieList
import com.example.fourchartest.domain.useCases.GetAllMovieInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllMovieInfoUseCase: GetAllMovieInfoUseCase,
) : ViewModel() {

    private val _isLoadingLifeData = MutableLiveData<Boolean>()
    val isLoadingLifeData: LiveData<Boolean>
        get() = _isLoadingLifeData

    private val _listMovie = MutableLiveData<List<MovieList>>()
    val listMovie: LiveData<List<MovieList>>
        get() = _listMovie

    fun getAllMovieList() {
        _isLoadingLifeData.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val lists = getAllMovieInfoUseCase()
            withContext((Dispatchers.Main)) {
                _isLoadingLifeData.value = false
                _listMovie.value = lists
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}