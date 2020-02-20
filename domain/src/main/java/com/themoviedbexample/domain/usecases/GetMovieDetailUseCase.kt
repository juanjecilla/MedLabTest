package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.BaseFlowableUseCase
import com.themoviedbexample.domain.common.FlowableRxTransformer
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Flowable

class GetMovieDetailUseCase(
    transformer: FlowableRxTransformer<MovieDetailEntity>,
    private val repositories: MovieRepository
) : BaseFlowableUseCase<MovieDetailEntity>(transformer) {

    override fun createFlowable(data: Map<String, Any>?): Flowable<MovieDetailEntity> {
        return repositories.getMovieDetail(data?.get("id") as String)
    }

    fun getMovieDetail(id: String): Flowable<MovieDetailEntity> {
        val data = HashMap<String, String>()
        data["id"] = id
        return single(data)
    }
}