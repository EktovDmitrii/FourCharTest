package com.example.fourchartest.data

import com.example.fourchartest.domain.MovieRepository
import com.example.fourchartest.domain.model.MovieDetailInfo
import com.example.fourchartest.domain.model.MovieInfo
import com.example.fourchartest.domain.model.MovieList
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : MovieRepository {

    override suspend fun getTopMovieInfoList(): List<MovieInfo> {
        return apiService.getTopMoviesInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getPopularMovieInfoList(): List<MovieInfo> {
        return apiService.getPopularMoviesInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getNowPlayingMovieInfoList(): List<MovieInfo> {
        return apiService.getNowPlayingMovieInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getUpcomingMovieInfoList(): List<MovieInfo> {
        return apiService.getUpcomingMovieInfo().movieList.map { it ->
            MovieInfo(
                id = it.id,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                voteAverage = it.voteAverage,
                genreIds = it.genreIds
            )
        }
    }

    override suspend fun getAllMovieListsInfo(): List<MovieList> {
        val getTop = MovieList("Top rated movies", getTopMovieInfoList())
        val getUpcoming = MovieList("Upcoming movies", getUpcomingMovieInfoList())
        val getPopular = MovieList("Popular movies", getPopularMovieInfoList())
        val getNowPlaying = MovieList("Now playing movies", getNowPlayingMovieInfoList())
        return listOf(getNowPlaying, getPopular, getTop, getUpcoming)
    }

    override suspend fun getDetails(movieId: Int): MovieDetailInfo {
        with(apiService.getDetails(movieId)) {
            return MovieDetailInfo(
                id = id,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
                video = video,
                overview = overview,
                backdropPath = backdropPath,
                genreIds = genreIds,
            )
        }
    }
}