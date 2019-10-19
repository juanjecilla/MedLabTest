package com.medlab.medlabtest.data.callbacks

import com.medlab.medlabtest.data.model.MovieItem

interface OnMovieItemListener {
    fun showMovieDetail(movieItem: MovieItem)
    fun updateFav(movieItem: MovieItem)
}
