package com.themoviedbexample.data.repository

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Flowable

class MovieRepositoryImpl(
    private val remote: MovieRemoteImpl,
    private val cache: MovieCacheImpl
) : MovieRepository {

    override fun getLocalMovieItems(): Flowable<MovieSourcesEntity> {
        return cache.getMovieItems()
    }

    override fun getRemoteMovieItems(): Flowable<MovieSourcesEntity> {
        return remote.getMovieItems()
    }

    override fun getMovieItems(): Flowable<MovieSourcesEntity> {
        val updateMovieItemsFlowable = remote.getMovieItems()
        return cache.getMovieItems()
            .mergeWith(updateMovieItemsFlowable.doOnNext { remoteMovieItems ->
                cache.saveArticles(remoteMovieItems)
            })
    }

    override fun getMovieDetail(id: String): Flowable<MovieDetailEntity> {
        return remote.getMovieDetail(id)
    }
}