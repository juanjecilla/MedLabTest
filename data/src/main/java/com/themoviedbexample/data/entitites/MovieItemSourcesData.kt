package com.themoviedbexample.data.entitites

import com.google.gson.annotations.SerializedName

data class MovieItemSourcesData(
    @SerializedName("page") var page: Int?,
    @SerializedName("total_results") var total_results: Int?,
    @SerializedName("total_pages") var total_pages: Int?,
    @SerializedName("results") var results: List<MovieItemData> = emptyList()
)
