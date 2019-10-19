package com.medlab.medlabtest.ui.main.list

import android.util.Log
import com.medlab.medlabtest.base.BasePresenter
import com.medlab.medlabtest.data.manager.DatabaseManager
import com.medlab.medlabtest.data.model.MovieItem
import com.medlab.medlabtest.data.source.DataSource
import java.util.*
import javax.inject.Inject

class MovieListPresenter @Inject constructor(private val mDatabaseManager: DatabaseManager) :
    BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {

    override fun getMovies(page: Int) {
        view?.setProgressBar(true)
        mDatabaseManager.getMovieItems(page, object : DataSource.GetMovieItemsCallback {
            override fun onSuccess(items: ArrayList<MovieItem>) {
                view?.setProgressBar(false)
                view?.showMovies(items)
                view?.shouldShowPlaceholderText()
            }

            override fun onFailure(throwable: Throwable) {
                Log.d(TAG, throwable.localizedMessage)
            }

            override fun onCancelled() {
                Log.d(TAG, "On cancelled")
            }
        })
    }

    fun updateFavourite(movieItem: MovieItem) {
        mDatabaseManager.toggleFavourite(movieItem)
    }

    companion object {
        const val TAG = "MovieListPresenter:"
    }
}