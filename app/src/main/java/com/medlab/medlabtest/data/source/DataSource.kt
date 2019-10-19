package com.medlab.medlabtest.data.source

import com.medlab.medlabtest.data.model.MovieItem

abstract class DataSource {

    interface GetMovieItemsCallback {
        fun onSuccess(items: ArrayList<MovieItem>)

        fun onFailure(throwable: Throwable)

        fun onCancelled()
    }

    interface GetMovieDetailCallback {
        fun onSuccess(movieItem: MovieItem)

        fun onFailure(throwable: Throwable)

        fun onCancelled()
    }

    interface FavouriteCallback {
        fun onSuccess(results: ArrayList<MovieItem>)

        fun onFailure(throwable: Throwable)
    }

    abstract fun getMovieItems(page: Int, callback: GetMovieItemsCallback)

    abstract fun getMovieDetail(id: String, callback: GetMovieDetailCallback)
}
