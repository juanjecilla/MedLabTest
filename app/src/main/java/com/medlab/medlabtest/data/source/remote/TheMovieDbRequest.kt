package com.medlab.medlabtest.data.source.remote

import android.util.Log

import com.google.gson.JsonElement
import com.medlab.medlabtest.data.source.DataSource

import java.util.HashMap

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDbRequest(
    private val mTheMovieDbAPI: TheMovieDbAPI, params: HashMap<String, String>,
    request: Int, private val mCallback: DataSource.TheMovieDbCallback
) : Callback<JsonElement> {

    private val mCall: Call<JsonElement>?

    init {
        this.mCall = getCall(params, request)
    }

    override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
        if (response.isSuccessful) {
            if (response.code() == 200) {
                if (response.body() != null) {
                    mCallback.onSuccess(response.body()!!)
                }
            }
        } else {
            mCallback.onFailure(Throwable())
        }
    }

    override fun onFailure(call: Call<JsonElement>, t: Throwable) {
        Log.e("ON_FAILURE", call.request().url().toString() + ": " + t.message)
        mCallback.onFailure(t)
    }

    private fun getCall(params: HashMap<String, String>, requestCode: Int): Call<JsonElement>? {

        var call: Call<JsonElement>? = null

        when (requestCode) {
            APICodes.GET_MOVIE_LIST -> call = mTheMovieDbAPI.getMovieList(params)
        }

        return call
    }

    fun enqueue() {
        mCall!!.enqueue(this)
    }
}
