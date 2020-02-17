package com.themoviedbexample.domain.repositories

import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Flowable

interface MovieRepository {

    fun getMovieItems(): Flowable<MovieSourcesEntity>
    fun getRemoteMovieItems(): Flowable<MovieSourcesEntity>
    fun getLocalMovieItems(): Flowable<MovieSourcesEntity>
}