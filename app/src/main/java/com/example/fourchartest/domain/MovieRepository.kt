package com.example.fourchartest.domain

import com.example.fourchartest.domain.model.MovieDetailInfo
import com.example.fourchartest.domain.model.MovieInfo
import com.example.fourchartest.domain.model.MovieList

interface MovieRepository {

    suspend fun getTopMovieInfoList(): List<MovieInfo>

    suspend fun getPopularMovieInfoList(): List<MovieInfo>

    suspend fun getNowPlayingMovieInfoList(): List<MovieInfo>

    suspend fun getUpcomingMovieInfoList(): List<MovieInfo>

    suspend fun getAllMovieListsInfo(): List<MovieList>

    suspend fun getDetails(movieId: Int): MovieDetailInfo

}