package com.example.fourchartest.domain.model

data class MovieInfo(
    var id: Int,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    val genreIds: List<Int>?,
)
