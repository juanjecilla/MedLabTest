package com.themoviedbexample.data.repository

import com.themoviedbexample.data.api.RemoteMovieApi
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Observable

class MovieRemoteImpl constructor(private val api: RemoteMovieApi) : MovieDataStore {

    private val mMovieMapper =
        MovieDataEntityMapper()

    override fun getMovieItems(): Observable<MovieSourcesEntity> {

        return api.getMovieItems().map { mMovieMapper.mapToEntity(it) }
    }

    override fun getMovieDetail(id: Long): Observable<MovieDetailEntity> {
        return api.getMovieDetail(id).map { mMovieMapper.mapToEntity(it) }
    }
}