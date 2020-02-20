package com.themoviedbexample.data.repository

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Observable

class MovieRepositoryImpl(
    private val remote: MovieRemoteImpl,
    private val cache: MovieCacheImpl
) : MovieRepository {

    override fun getLocalMovieItems(): Observable<MovieSourcesEntity> {
        return cache.getMovieItems()
    }

    override fun getRemoteMovieItems(): Observable<MovieSourcesEntity> {
        return remote.getMovieItems()
    }

    override fun getMovieItems(): Observable<MovieSourcesEntity> {
        val updateMovieItemsObservable = remote.getMovieItems()
        return cache.getMovieItems()
            .mergeWith(updateMovieItemsObservable.doOnNext { remoteMovieItems ->
                cache.saveArticles(remoteMovieItems)
            })
    }

    override fun getMovieDetail(id: Long): Observable<MovieDetailEntity> {
        return remote.getMovieDetail(id)
    }
}