package com.example.fourchartest.data.dto

import com.google.gson.annotations.SerializedName

data class MovieInfoListDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movieList: List<MovieInfoDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
)