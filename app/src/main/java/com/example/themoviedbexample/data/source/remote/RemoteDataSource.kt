package com.example.themoviedbexample.data.source.remote

import com.example.themoviedbexample.data.manager.TheMovieDbManager
import com.example.themoviedbexample.data.source.DataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject
constructor(private val mTheMovieDbManager: TheMovieDbManager) : DataSource() {
    override fun getMovieItems(page: Int, callback: GetMovieItemsCallback) {
        mTheMovieDbManager.getMovieItems(page, callback)
    }

    override fun getMovieDetail(id: Long, callback: GetMovieDetailCallback) {
        mTheMovieDbManager.getMovieDetail(id, callback)
    }
}
