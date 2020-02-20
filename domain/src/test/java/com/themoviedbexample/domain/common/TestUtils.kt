package com.themoviedbexample.domain.common

import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieItemEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity

/**
 * Created by Yossi Segev on 09/02/2018.
 */
class TestUtils {

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

        fun getTestMovieItemsEntities(size: Int): List<MovieItemEntity> {
            val entities = ArrayList<MovieItemEntity>()
            for (i in 0 until size) {
                val entity = MovieItemEntity(
                    id = i.toLong(),
                    title = "Title $i",
                    posterPath = ""
                )
                entities.add(entity)
            }
            return entities
        }
    }
}