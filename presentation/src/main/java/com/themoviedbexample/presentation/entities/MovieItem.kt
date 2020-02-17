package com.themoviedbexample.presentation.entities

data class MovieItem(
    var id: Long? = 0,
    var title: String? = null,
    var posterPath: String? = null
)