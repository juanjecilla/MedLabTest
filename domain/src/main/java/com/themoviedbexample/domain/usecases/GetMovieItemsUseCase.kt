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

    override fun createObservable(data: Map<String, Any>?): Observable<MovieSourcesEntity> {
        return repositories.getMovieItems()
    }

    fun getMovieItems(): Observable<MovieSourcesEntity> {
        val data = HashMap<String, Any>()
        return observable(data)
    }
}