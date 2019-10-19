package com.medlab.medlabtest.data.manager

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.medlab.medlabtest.data.model.MovieItem
import com.medlab.medlabtest.data.source.DataSource
import com.medlab.medlabtest.data.source.remote.APICodes
import com.medlab.medlabtest.data.source.remote.TheMovieDbAPI
import com.medlab.medlabtest.data.source.remote.TheMovieDbRequest
import javax.inject.Inject
import javax.inject.Singleton
import com.google.gson.reflect.TypeToken



@Singleton
class TheMovieDbManager @Inject
constructor(
    private val mTheMovieDbAPI: TheMovieDbAPI
) {

    fun getMovieItems(page: Int, callback: DataSource.GetMovieItemsCallback) {

        val params = HashMap<String, String>()
        params["api_key"] = "b66ffea8276ce576d60df52600822c88"
        if (page != 0) {
            params["page"] = page.toString()
        }

        val request = TheMovieDbRequest(
            mTheMovieDbAPI,
            params,
            APICodes.GET_MOVIE_LIST,
            object : DataSource.TheMovieDbCallback {
                override fun onSuccess(results: JsonElement) {
                    val movieResults: ArrayList<MovieItem> = ArrayList(Gson().fromJson(results.asJsonObject.getAsJsonArray("results"), Array<MovieItem>::class.java).toList())
                    callback.onSuccess(movieResults)
                }

                override fun onFailure(throwable: Throwable) {
                    callback.onFailure(throwable)
                }
            })
        request.enqueue()
    }

    companion object {
        private const val TAG = "TheMovieDbManager: "
    }
}
