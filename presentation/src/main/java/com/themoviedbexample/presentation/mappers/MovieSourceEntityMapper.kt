package com.themoviedbexample.presentation.mappers

import com.themoviedbexample.domain.common.Mapper
import com.themoviedbexample.domain.entities.MovieItemEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.presentation.entities.MovieItem
import com.themoviedbexample.presentation.entities.MovieSources

class MovieSourceEntityMapper : Mapper<MovieSourcesEntity, MovieSources>() {
    override fun mapFrom(from: MovieSourcesEntity): MovieSources = MovieSources(
        page = from.page,
        total_pages = from.total_pages,
        total_results = from.total_results,
        results = mapListArticlesToPresetation(from.results)
    )

    private fun mapListArticlesToPresetation(articles: List<MovieItemEntity>?)
            : List<MovieItem> = articles?.map { mapArticleToPresentation(it) }
        ?: emptyList()

    private fun mapArticleToPresentation(response: MovieItemEntity): MovieItem = MovieItem(
        id = response.id,
        title = response.title,
        posterPath = response.posterPath
    )

}