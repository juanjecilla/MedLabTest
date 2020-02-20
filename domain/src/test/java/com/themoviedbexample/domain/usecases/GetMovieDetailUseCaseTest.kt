package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.FlowableRxTransformer
import com.themoviedbexample.domain.common.mock
import com.themoviedbexample.domain.common.whenever
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito

class GetMovieDetailUseCaseTest {


    private val transformer = mock<FlowableRxTransformer<MovieDetailEntity>>()
    private val repository = mock<MovieRepository>()

    private val getMovieDetailUseCase by lazy { GetMovieDetailUseCase(transformer, repository) }


    @Before
    fun setUp() {
    }

    @Test
    fun createFlowable() {
        val flowable = mock<Flowable<MovieDetailEntity>>()
        whenever(repository.getMovieDetail(anyString()))
            .thenReturn(flowable)

        val ret = getMovieDetailUseCase.getMovieDetail("")

        val testSubscriber = getMovieDetailUseCase.getMovieDetail("dummy").test()

        testSubscriber.awaitTerminalEvent()

        testSubscriber.assertComplete()
    }

    @Test
    fun getMovieDetail() {

    }
}