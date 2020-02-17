package com.themoviedbexample.data.repository

import com.themoviedbexample.data.db.MovieDao
import com.themoviedbexample.data.db.MovieDatabase
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.data.mappers.MovieEntityDataMapper
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Flowable

class MovieCacheImpl(
    private val database: MovieDatabase,
    private val entityToDataMapper: MovieEntityDataMapper,
    private val dataToEntityMapper: MovieDataEntityMapper
) : MovieDataStore {

    private val dao: MovieDao = database.getArticlesDao()

    override fun getNews(): Flowable<MovieSourcesEntity> {
        return dao.getAllArticles().map { it ->
            dataToEntityMapper.mapToEntity(it)
        }
    }

    fun saveArticles(it: MovieSourcesEntity) {
        dao.clear()
        dao.saveAllArticles(it.results.map { articles ->
            entityToDataMapper.mapArticleToEntity(
                articles
            )
        })
    }

}