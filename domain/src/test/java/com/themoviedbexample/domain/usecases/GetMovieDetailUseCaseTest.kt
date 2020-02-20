package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.TestEntityUtils
import com.themoviedbexample.domain.common.TestTransformer
import com.themoviedbexample.domain.common.mock
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetMovieDetailUseCaseTest {

    private lateinit var mRepository: MovieRepository

    @Before
    fun setUp() {
        mRepository = mock<MovieRepository>()
    }

    @Test
    fun getMovieDetail() {
        val movieEntity = TestEntityUtils.getTestMovieEntity(100)
        val getMovieDetailUseCase = GetMovieDetailUseCase(TestTransformer(), mRepository)

        Mockito.`when`(mRepository.getMovieDetail(100)).thenReturn(Observable.just(movieEntity))

        getMovieDetailUseCase.getMovieDetail(100).test()
            .assertValue { returnedMovieEntity ->
                returnedMovieEntity.id == 100L
            }
            .assertComplete()
    }
}