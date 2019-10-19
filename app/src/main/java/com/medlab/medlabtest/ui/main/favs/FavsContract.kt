package com.medlab.medlabtest.ui.main.favs

import com.medlab.medlabtest.base.IBasePresenter
import com.medlab.medlabtest.base.IBaseView
import com.medlab.medlabtest.data.model.MovieItem
import java.util.*

interface FavsContract {
    interface View : IBaseView {

        fun showFavorites(movieItems: ArrayList<MovieItem>)
        fun shouldShowPlaceholderText()
    }

    interface Presenter : IBasePresenter<View> {

        fun getFavourites()
    }
}