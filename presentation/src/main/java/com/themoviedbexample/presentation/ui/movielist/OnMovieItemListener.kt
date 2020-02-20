package com.themoviedbexample.presentation.ui.movielist

import com.themoviedbexample.presentation.entities.MovieItem

interface OnMovieItemListener {
    fun showMovieDetail(movieItem: MovieItem)
}
