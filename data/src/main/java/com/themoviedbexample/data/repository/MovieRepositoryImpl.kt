package com.themoviedbexample.data.repository

import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Flowable

class MovieRepositoryImpl(
    private val remote: MovieRemoteImpl,
    private val cache: MovieCacheImpl
) : MovieRepository {

    override fun getLocalMovieItems(): Flowable<MovieSourcesEntity> {
        return cache.getNews()
    }

    override fun getRemoteMovieItems(): Flowable<MovieSourcesEntity> {
        return remote.getNews()
    }

    override fun getMovieItems(): Flowable<MovieSourcesEntity> {
        val updateNewsFlowable = remote.getNews()
        return cache.getNews()
            .mergeWith(updateNewsFlowable.doOnNext { remoteNews ->
                cache.saveArticles(remoteNews)
            })
    }
}