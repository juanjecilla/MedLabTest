package com.themoviedbexample.data.utils

import com.themoviedbexample.data.entitites.MovieDetailData
import com.themoviedbexample.data.entitites.MovieItemData
import com.themoviedbexample.data.entitites.MovieItemSourcesData

/**
 * Created by Yossi Segev on 09/02/2018.
 */
class TestEntityUtils {

    companion object {
        fun getTestMovieEntity(id: Long): MovieDetailData {
            return MovieDetailData(
                id = id,
                title = "Movie$id",
                posterPath = "",
                overview = ""
            )
        }

        fun getTestMovieSourceEntity(size: Int): MovieItemSourcesData {
            return MovieItemSourcesData(
                page = 0,
                total_pages = 0,
                total_results = size,
                results = getTestMovieItemsEntities(size)
            )
        }

        private fun getTestMovieItemsEntities(size: Int): List<MovieItemData> {
            return (0..size).map {
                MovieItemData(
                    id = it.toLong(),
                    title = "Title $it",
                    posterPath = ""
                )
            }
        }
    }
}