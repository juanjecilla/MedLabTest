package com.medlab.medlabtest.ui.list

import android.util.Log
import com.medlab.medlabtest.base.BasePresenter
import com.medlab.medlabtest.data.manager.DatabaseManager
import com.medlab.medlabtest.data.model.MovieItem
import com.medlab.medlabtest.data.source.DataSource
import java.util.ArrayList
import javax.inject.Inject

class ListPresenter @Inject constructor(private val mDatabaseManager : DatabaseManager) :
    BasePresenter<ListContract.View>(), ListContract.Presenter {

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
        const val TAG = "ListPresenter:"
    }
}