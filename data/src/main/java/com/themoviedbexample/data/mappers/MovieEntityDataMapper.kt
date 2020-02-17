package com.themoviedbexample.data.mappers

import com.themoviedbexample.data.entitites.MovieItemData
import com.themoviedbexample.data.entitites.MovieItemSourcesData
import com.themoviedbexample.domain.entities.MovieItemEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity


class MovieEntityDataMapper constructor() {

    fun mapToEntity(data: MovieSourcesEntity?): MovieItemSourcesData? = MovieItemSourcesData(
        page = data?.page,
        total_results = data?.total_results,
        total_pages = data?.total_pages,
        results = mapListArticlesToEntity(data?.results)
    )

    private fun mapListArticlesToEntity(articles: List<MovieItemEntity>?)
            : List<MovieItemData> = articles?.map { mapArticleToEntity(it) } ?: emptyList()

    fun mapArticleToEntity(response: MovieItemEntity): MovieItemData = MovieItemData(
        id = response.id,
        posterPath = response.posterPath,
        title = response.title
    )
}