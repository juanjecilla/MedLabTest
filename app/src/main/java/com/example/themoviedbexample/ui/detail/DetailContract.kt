package com.example.themoviedbexample.ui.detail

import com.example.themoviedbexample.base.IBasePresenter
import com.example.themoviedbexample.base.IBaseView
import com.example.themoviedbexample.data.model.MovieDetail

interface DetailContract {
    interface View : IBaseView {

        fun showMovieDetail(movie: MovieDetail)
    }

    interface Presenter : IBasePresenter<View> {

        fun getMovieDetail(id: Long)
    }
}
