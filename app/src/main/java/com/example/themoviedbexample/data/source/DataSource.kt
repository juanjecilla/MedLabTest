package com.example.themoviedbexample.data.source

import com.google.gson.JsonElement
import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.data.model.MovieItem

abstract class DataSource {

    interface GetMovieItemsCallback {
        fun onSuccess(items: ArrayList<MovieItem>)

        fun onFailure(throwable: Throwable)

        fun onCancelled()
    }

    interface GetMovieDetailCallback {
        fun onSuccess(movie: MovieDetail)

        fun onFailure(throwable: Throwable)

        fun onCancelled()
    }

    interface FavouriteCallback {
        fun onSuccess(results: ArrayList<MovieItem>)

        fun onFailure(throwable: Throwable)
    }

    interface TheMovieDbCallback {
        fun onSuccess(results: JsonElement)

        fun onFailure(throwable: Throwable)
    }

    abstract fun getMovieItems(page: Int, callback: GetMovieItemsCallback)

    abstract fun getMovieDetail(id: Long, callback: GetMovieDetailCallback)
}
