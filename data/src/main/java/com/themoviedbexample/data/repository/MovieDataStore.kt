package com.themoviedbexample.data.repository

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Observable

interface MovieDataStore {
    fun getMovieItems(): Observable<MovieSourcesEntity>
    fun getMovieDetail(id: Long): Observable<MovieDetailEntity>
}