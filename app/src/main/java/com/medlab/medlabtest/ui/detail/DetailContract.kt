package com.medlab.medlabtest.ui.detail

import com.medlab.medlabtest.base.IBasePresenter
import com.medlab.medlabtest.base.IBaseView
import com.medlab.medlabtest.data.model.MovieDetail

interface DetailContract {
    interface View : IBaseView {

        fun showMovieDetail(movie: MovieDetail)
    }

    interface Presenter : IBasePresenter<View> {

        fun getMovieDetail(id: Long)
    }
}
