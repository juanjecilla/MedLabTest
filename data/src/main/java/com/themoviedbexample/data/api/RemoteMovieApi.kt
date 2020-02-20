package com.themoviedbexample.data.api

import com.themoviedbexample.data.entitites.MovieDetailData
import com.themoviedbexample.data.entitites.MovieItemSourcesData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RemoteMovieApi {

    @GET("/3/movie/popular")
    fun getMovieItems(): Observable<MovieItemSourcesData>

    @GET("/3/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Long): Observable<MovieDetailData>

}