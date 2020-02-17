package com.themoviedbexample.data.repository

import com.themoviedbexample.data.api.RemoteMovieApi
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Flowable

class MovieRemoteImpl constructor(private val api: RemoteMovieApi) : MovieDataStore {

    private val mMovieItemsMapper =
        MovieDataEntityMapper()

    override fun getMovieItems(): Flowable<MovieSourcesEntity> {

        return api.getMovieItems().map { mMovieItemsMapper.mapToEntity(it) }
    }

}