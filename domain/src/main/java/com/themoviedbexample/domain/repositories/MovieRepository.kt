package com.themoviedbexample.domain.repositories

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Observable

interface MovieRepository {

    fun getMovieItems(data: Map<String, String>): Observable<MovieSourcesEntity>
    fun getRemoteMovieItems(data: Map<String, String>): Observable<MovieSourcesEntity>
    fun getLocalMovieItems(): Observable<MovieSourcesEntity>

    fun getMovieDetail(id: Long): Observable<MovieDetailEntity>
}