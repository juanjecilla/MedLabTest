package com.example.themoviedbexample.data.manager

import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.data.source.DataSource
import com.example.themoviedbexample.data.source.remote.APICodes
import com.example.themoviedbexample.data.source.remote.TheMovieDbAPI
import com.example.themoviedbexample.data.source.remote.TheMovieDbRequest
import com.google.gson.Gson
import com.google.gson.JsonElement
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TheMovieDbManager @Inject
constructor(
    private val mTheMovieDbAPI: TheMovieDbAPI
) {

    fun getMovieItems(page: Int, callback: DataSource.GetMovieItemsCallback) {

        val params = HashMap<String, String>()
        params["api_key"] = API_KEY
        if (page != 0) {
            params["page"] = page.toString()
        }

        val request = TheMovieDbRequest(
            mTheMovieDbAPI,
            params, HashMap(),
            APICodes.GET_MOVIE_LIST,
            object : DataSource.TheMovieDbCallback {
                override fun onSuccess(results: JsonElement) {
                    val movieResults: ArrayList<MovieItem> = ArrayList(
                        Gson().fromJson(
                            results.asJsonObject.getAsJsonArray("results"),
                            Array<MovieItem>::class.java
                        ).toList()
                    )
                    callback.onSuccess(movieResults)
                }

                override fun onFailure(throwable: Throwable) {
                    callback.onFailure(throwable)
                }
            })
        request.enqueue()
    }

    fun getMovieDetail(id: Long, callback: DataSource.GetMovieDetailCallback) {

        val params = HashMap<String, String>()
        val path = HashMap<String, String>()
        params["api_key"] = API_KEY
        path["movie_id"] = id.toString()

        val request = TheMovieDbRequest(
            mTheMovieDbAPI,
            params, path,
            APICodes.GET_MOVIE_DETAIL,
            object : DataSource.TheMovieDbCallback {
                override fun onSuccess(results: JsonElement) {
                    val movieDetail = Gson().fromJson(results.asJsonObject, MovieDetail::class.java)
                    callback.onSuccess(movieDetail)
                }

                override fun onFailure(throwable: Throwable) {
                    callback.onFailure(throwable)
                }
            })
        request.enqueue()

    }

    companion object {
        private const val TAG = "TheMovieDbManager: "
        private const val API_KEY = "b66ffea8276ce576d60df52600822c88"
    }
}
