package com.themoviedbexample.presentation.mappers

import com.themoviedbexample.domain.common.Mapper
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.presentation.entities.MovieDetail

class MovieDetailEntityMapper : Mapper<MovieDetailEntity, MovieDetail>() {
    override fun mapFrom(from: MovieDetailEntity): MovieDetail = MovieDetail(
        id = from.id,
        title = from.title,
        posterPath = from.posterPath,
        overview = from.overview
    )
}