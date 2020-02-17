package com.themoviedbexample.presentation.entities

data class MovieSources(
    var page: Int? = null,
    var total_results: Int? = null,
    var total_pages: Int? = null,
    var results: List<MovieItem> = emptyList()
)