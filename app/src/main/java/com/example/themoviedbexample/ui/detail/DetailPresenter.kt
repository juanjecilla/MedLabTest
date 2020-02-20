package com.example.themoviedbexample.ui.detail

import android.util.Log
import com.example.themoviedbexample.base.BasePresenter
import com.example.themoviedbexample.data.callbacks.OnDefaultCallback
import com.example.themoviedbexample.data.manager.DatabaseManager
import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.data.source.DataSource
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val mDatabaseManager: DatabaseManager) :
    BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    override fun getMovieDetail(id: Long) {

        mDatabaseManager.getMovieDetail(id, object : DataSource.GetMovieDetailCallback {

            override fun onSuccess(movie: MovieDetail) {
                view?.setProgressBar(false)
                view?.showMovieDetail(movie)
            }

            override fun onFailure(throwable: Throwable) {
                view?.setProgressBar(false)
                view?.showToastMessage("Error")

                Log.d(TAG, throwable.message)
            }

            override fun onCancelled() {
                view?.setProgressBar(false)
                view?.showToastMessage("Error")
            }
        })

    }

    override fun updateFavorite(movieItem: MovieItem) {
        mDatabaseManager.toggleFavourite(movieItem, object : OnDefaultCallback<Boolean> {
            override fun onSuccess(result: Boolean) {
                view?.checkFavourite(result)
            }

            override fun onError(error: String) {
                view?.showToastMessage(error)
            }
        })
    }

    companion object {
        const val TAG = "DetailPresenter; "
    }
}


