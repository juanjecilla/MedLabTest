package com.example.themoviedbexample.data.manager

import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.data.source.DataSource
import com.example.themoviedbexample.data.source.local.LocalDataSource
import com.example.themoviedbexample.data.source.remote.RemoteDataSource
import java.util.*
import javax.inject.Inject

class DatabaseManager @Inject
constructor(
    private val mRemoteDataSource: RemoteDataSource,
    private val mLocalDataSource: LocalDataSource
) {

    fun getMovieItems(page: Int, callback: DataSource.GetMovieItemsCallback) {
        mRemoteDataSource.getMovieItems(page, object : DataSource.GetMovieItemsCallback {
            override fun onSuccess(items: ArrayList<MovieItem>) {
                for (item: MovieItem in items) {
                    item.isFavorite = mLocalDataSource.isFavorite(item)
                }
                callback.onSuccess(items)
            }

            override fun onFailure(throwable: Throwable) {
                callback.onFailure(throwable)
            }

            override fun onCancelled() {
                callback.onCancelled()
            }
        })
    }

    fun getMovieDetail(id: Long, callback: DataSource.GetMovieDetailCallback) {
        mRemoteDataSource.getMovieDetail(id, callback)
    }

    fun getFavourites(callback: DataSource.FavouriteCallback) {
        mLocalDataSource.getFavorites(callback)
    }

    fun toggleFavourite(movieItem: MovieItem) {
        mLocalDataSource.toggleFavorite(movieItem)
    }
}
