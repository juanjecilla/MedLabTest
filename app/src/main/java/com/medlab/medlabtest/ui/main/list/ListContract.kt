package com.medlab.medlabtest.ui.main.list

import com.medlab.medlabtest.base.IBasePresenter
import com.medlab.medlabtest.base.IBaseView
import com.medlab.medlabtest.data.model.MovieItem

interface ListContract {
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