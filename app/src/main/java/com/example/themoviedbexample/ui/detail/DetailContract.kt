package com.example.themoviedbexample.ui.detail

import com.example.themoviedbexample.base.IBasePresenter
import com.example.themoviedbexample.base.IBaseView
import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.data.model.MovieItem

interface DetailContract {
    interface View : IBaseView {

        fun showMovieDetail(movie: MovieDetail)
        fun checkFavourite(favorite: Boolean)
    }

    interface Presenter : IBasePresenter<View> {

        fun getMovieDetail(id: Long)
        fun updateFavorite(movieItem: MovieItem)
    }
}
