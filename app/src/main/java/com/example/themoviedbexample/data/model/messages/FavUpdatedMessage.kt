package com.example.themoviedbexample.data.model.messages

import com.example.themoviedbexample.data.model.MovieItem

data class FavUpdatedMessage(
    var uid: String,
    var item: MovieItem
)
