package com.example.fourchartest.data.dto

import com.google.gson.annotations.SerializedName

data class MovieInfoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("video") val video: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
)
