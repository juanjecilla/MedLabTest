package com.themoviedbexample.data.api

import com.themoviedbexample.data.entitites.MovieItemSourcesData
import io.reactivex.Flowable
import retrofit2.http.GET

interface RemoteMovieApi {

    @GET("/3/movie/popular")
    fun getNews(): Flowable<MovieItemSourcesData>

}