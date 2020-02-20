package com.themoviedbexample.data.mappers

import com.themoviedbexample.data.entitites.MovieDetailData
import com.themoviedbexample.data.entitites.MovieItemData
import com.themoviedbexample.data.entitites.MovieItemSourcesData
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieItemEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity

class MovieDataEntityMapper constructor() {

    fun mapToEntity(data: MovieItemSourcesData?): MovieSourcesEntity? = MovieSourcesEntity(
        page = data?.page,
        total_results = data?.total_results,
        total_pages = data?.total_pages,
        results = mapListResultsToEntity(data?.results)
    )

    fun mapToEntity(articles: List<MovieItemData>?): MovieSourcesEntity? = MovieSourcesEntity(
        results = mapListResultsToEntity(articles)
    )

    private fun mapListResultsToEntity(articles: List<MovieItemData>?)
            : List<MovieItemEntity> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    private fun mapArticleToEntity(response: MovieItemData): MovieItemEntity = MovieItemEntity(
        id = response.id,
        title = response.title,
        posterPath = response.posterPath
    )

    fun mapToEntity(data: MovieDetailData?): MovieDetailEntity? = MovieDetailEntity(
        id = data?.id,
        title = data?.title,
        posterPath = data?.posterPath,
        overview = data?.overview
    )
}
