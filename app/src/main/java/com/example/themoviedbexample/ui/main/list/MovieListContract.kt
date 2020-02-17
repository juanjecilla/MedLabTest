package com.example.themoviedbexample.ui.main.list

import com.example.themoviedbexample.base.IBasePresenter
import com.example.themoviedbexample.base.IBaseView
import com.example.themoviedbexample.data.model.MovieItem

interface MovieListContract {
    interface View : IBaseView {

        fun showMovies(movieItems: ArrayList<MovieItem>)
        fun showMovieDetail(movieItem: MovieItem)
        fun shouldShowPlaceholderText()
        fun updateFav(item: MovieItem)
    }

    interface Presenter : IBasePresenter<View> {

        fun getMovies(page: Int)
    }
}