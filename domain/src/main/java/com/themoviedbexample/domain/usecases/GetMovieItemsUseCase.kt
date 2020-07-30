package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.BaseUseCase
import com.themoviedbexample.domain.common.Transformer
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Observable

class GetMovieItemsUseCase(
    transformer: Transformer<MovieSourcesEntity>,
    private val repositories: MovieRepository
) : BaseUseCase<MovieSourcesEntity>(transformer) {

    override fun createObservable(data: Map<String, String>?): Observable<MovieSourcesEntity> {
        return data?.let {
            repositories.getMovieItems(data)
        } ?: throw IllegalArgumentException()
    }

    fun getMovieItems(page: Int): Observable<MovieSourcesEntity> {
        val data = HashMap<String, String>()
        data[PARAM_PAGE] = page.toString()
        return observable(data)
    }

    companion object {
        private const val PARAM_PAGE = "page"
    }
}