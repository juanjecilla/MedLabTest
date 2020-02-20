package com.themoviedbexample.data.api

import com.google.gson.JsonElement
import com.themoviedbexample.data.entitites.MovieDetailData
import com.themoviedbexample.data.entitites.MovieItemSourcesData
import com.themoviedbexample.domain.entities.MovieDetailEntity
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import java.util.HashMap

interface RemoteMovieApi {

    @GET("/3/movie/popular")
    fun getMovieItems(): Flowable<MovieItemSourcesData>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: String): Flowable<MovieDetailData>

}