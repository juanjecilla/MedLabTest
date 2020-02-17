package com.example.themoviedbexample.ui.main.favs

import com.example.themoviedbexample.base.BasePresenter
import com.example.themoviedbexample.data.manager.DatabaseManager
import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.data.source.DataSource
import java.util.*
import javax.inject.Inject

class FavsPresenter @Inject constructor(private val mDatabaseManager: DatabaseManager) :
    BasePresenter<FavsContract.View>(), FavsContract.Presenter {

    override fun getFavourites() {
        view?.setProgressBar(true)
        mDatabaseManager.getFavourites(object : DataSource.FavouriteCallback {
            override fun onFailure(throwable: Throwable) {
            }

            override fun onSuccess(movieItems: ArrayList<MovieItem>) {
                view?.setProgressBar(false)
                view?.showFavorites(movieItems)
                view?.shouldShowPlaceholderText()
            }
        })
    }

    fun updateFavourite(movieItem: MovieItem) {
        mDatabaseManager.toggleFavourite(movieItem)
        view?.shouldShowPlaceholderText()
    }
}
