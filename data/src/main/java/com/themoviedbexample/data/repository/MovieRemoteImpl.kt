package com.themoviedbexample.data.repository

import com.themoviedbexample.data.api.RemoteMovieApi
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Flowable

class MovieRemoteImpl constructor(private val api: RemoteMovieApi) : MovieDataStore {

    private val newsMapper =
        MovieDataEntityMapper()

    override fun getNews(): Flowable<MovieSourcesEntity> {

        return api.getNews().map { newsMapper.mapToEntity(it) }
    }

}