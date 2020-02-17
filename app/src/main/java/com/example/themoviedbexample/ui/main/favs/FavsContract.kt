package com.example.themoviedbexample.ui.main.favs

import com.example.themoviedbexample.base.IBasePresenter
import com.example.themoviedbexample.base.IBaseView
import com.example.themoviedbexample.data.model.MovieItem
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