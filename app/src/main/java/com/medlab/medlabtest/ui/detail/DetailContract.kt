package com.medlab.medlabtest.ui.detail

import com.medlab.medlabtest.base.IBasePresenter
import com.medlab.medlabtest.base.IBaseView
import com.medlab.medlabtest.data.model.MovieDetail
import com.medlab.medlabtest.data.model.MovieItem

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
