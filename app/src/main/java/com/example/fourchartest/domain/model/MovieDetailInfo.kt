package com.example.fourchartest.domain.model

data class MovieDetailInfo(
    var id: Int,
    var posterPath: String?,
    var releaseDate: String,
    var title: String,
    var voteAverage: Double,
    val video: Boolean,
    val overview: String,
    val backdropPath: String?,
    val genreIds: List<Int>?,
)
