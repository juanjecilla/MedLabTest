package com.themoviedbexample.data.repository

import com.themoviedbexample.data.db.MovieDao
import com.themoviedbexample.data.db.MovieDatabase
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.data.mappers.MovieEntityDataMapper
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import io.reactivex.Observable

class MovieCacheImpl(
    database: MovieDatabase,
    private val entityToDataMapper: MovieEntityDataMapper,
    private val dataToEntityMapper: MovieDataEntityMapper
) : MovieDataStore {

    private val dao: MovieDao = database.getMoviesDao()

    override fun getMovieItems(data: Map<String, String>): Observable<MovieSourcesEntity> {
        return dao.getAllArticles().map {
            dataToEntityMapper.mapToEntity(it)
        }
    }

    override fun getMovieDetail(id: Long): Observable<MovieDetailEntity> {
        return dao.getMovieDetail(id).map { dataToEntityMapper.mapToEntity(it) }
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