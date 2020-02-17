package com.themoviedbexample.data.repository

import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Flowable

interface MovieDataStore {
    fun getNews(): Flowable<MovieSourcesEntity>
}