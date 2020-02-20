package com.example.themoviedbexample.data.callbacks

import com.example.themoviedbexample.data.model.MovieItem

interface OnMovieItemListener {
    fun showMovieDetail(movieItem: MovieItem)
    fun updateFav(movieItem: MovieItem)
}
