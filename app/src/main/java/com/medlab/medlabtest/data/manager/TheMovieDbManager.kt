package com.medlab.medlabtest.data.manager

import android.util.Log
import com.medlab.medlabtest.data.source.DataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TheMovieDbManager @Inject
constructor(
) {

    private val TAG = "TheMovieDbManager: "

    private var mRequestingItems: Boolean = false

    init {

    }

    fun getMovieItems(page: Int, callback: DataSource.GetMovieItemsCallback) {

        if (!mRequestingItems) {
            mRequestingItems = true

        } else {
            Log.d(TAG, "TheMovieDbManager is busy!")
        }

    }
}
