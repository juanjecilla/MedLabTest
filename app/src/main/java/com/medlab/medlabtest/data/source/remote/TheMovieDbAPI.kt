package com.medlab.medlabtest.data.source.remote

import com.google.gson.JsonElement

import java.util.HashMap

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TheMovieDbAPI {

    @GET("/3/movie/popular")
    fun getMovieList(@QueryMap params: HashMap<String, String>): Call<JsonElement>
}
