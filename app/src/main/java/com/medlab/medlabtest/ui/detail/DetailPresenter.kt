package com.medlab.medlabtest.ui.detail

import android.util.Log
import com.medlab.medlabtest.base.BasePresenter
import com.medlab.medlabtest.data.manager.DatabaseManager
import com.medlab.medlabtest.data.model.MovieDetail
import com.medlab.medlabtest.data.source.DataSource
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val mDatabaseManager: DatabaseManager) :
    BasePresenter<DetailContract.View>(), DetailContract.Presenter {

    val TAG = "DetailPresenter; "

    override fun getMovieDetail(id: Long) {

        mDatabaseManager.getMovieDetail(id, object : DataSource.GetMovieDetailCallback  {

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
}

