package com.example.fourchartest.domain.useCases

import com.example.fourchartest.domain.MovieRepository
import javax.inject.Inject

class GetPopularMovieInfoUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.getPopularMovieInfoList()
}