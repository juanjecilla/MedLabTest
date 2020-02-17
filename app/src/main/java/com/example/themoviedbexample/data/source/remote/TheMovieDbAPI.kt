package com.example.themoviedbexample.data.source.remote

import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.*

interface TheMovieDbAPI {

    @GET("/3/movie/popular")
    fun getMovieList(@QueryMap params: HashMap<String, String>): Call<JsonElement>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: String, @QueryMap params: HashMap<String, String>): Call<JsonElement>
}
