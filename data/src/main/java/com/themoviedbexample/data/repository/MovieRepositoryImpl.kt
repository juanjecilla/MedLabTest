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

    override fun getRemoteMovieItems(data: Map<String, String>): Observable<MovieSourcesEntity> {
        return remote.getMovieItems(data)
    }

    override fun getMovieItems(data: Map<String, String>): Observable<MovieSourcesEntity> {
        val updateMovieItemsObservable = remote.getMovieItems(data)
        return cache.getMovieItems(data).takeUntil(updateMovieItemsObservable)
            .mergeWith(updateMovieItemsObservable.doOnNext { remoteMovieItems ->
                cache.saveArticles(remoteMovieItems)
            })
    }

    override fun getMovieDetail(id: Long): Observable<MovieDetailEntity> {
        return remote.getMovieDetail(id)
    }
}