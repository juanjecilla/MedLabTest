package com.themoviedbexample.presentation.utils

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieItemEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity

/**
 * Created by Yossi Segev on 09/02/2018.
 */
class TestEntityUtils {

    companion object {
        fun getTestMovieEntity(id: Long): MovieDetailEntity {
            return MovieDetailEntity(
                id = id,
                title = "Movie$id",
                posterPath = "",
                overview = ""
            )
        }

        fun getTestMovieSourceEntity(size: Int): MovieSourcesEntity {
            return MovieSourcesEntity(
                page = 0,
                results = getTestMovieItemsEntities(size)
            )
        }

        private fun getTestMovieItemsEntities(size: Int): List<MovieItemEntity> {
            return (0..size).map {
                MovieItemEntity(
                    id = it.toLong(),
                    title = "Title $it",
                    posterPath = "")
            }
        }
    }
}