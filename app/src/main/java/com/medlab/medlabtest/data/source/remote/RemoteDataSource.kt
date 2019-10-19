package com.medlab.medlabtest.data.source.remote

import com.medlab.medlabtest.data.manager.TheMovieDbManager
import com.medlab.medlabtest.data.source.DataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject
constructor(private val mTheMovieDbManager: TheMovieDbManager) : DataSource() {
    override fun getMovieItems(page: Int, callback: GetMovieItemsCallback) {
        mTheMovieDbManager.getMovieItems(page, callback)
    }

    override fun getMovieDetail(id: String, callback: GetMovieDetailCallback) {
    }
}
