package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.BaseUseCase
import com.themoviedbexample.domain.common.Transformer
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Observable

class GetMovieDetailUseCase(
    transformer: Transformer<MovieDetailEntity>,
    private val repositories: MovieRepository
) : BaseUseCase<MovieDetailEntity>(transformer) {

    override fun createObservable(data: Map<String, String>?): Observable<MovieDetailEntity> {
        return repositories.getMovieDetail(data?.get(ID_KEY)?.toLong()?:0)
    }

    fun getMovieDetail(id: Long): Observable<MovieDetailEntity> {
        val data = HashMap<String, String>()
        data[ID_KEY] = id.toString()
        return observable(data)
    }

    companion object {
        private const val ID_KEY = "id"
    }
}