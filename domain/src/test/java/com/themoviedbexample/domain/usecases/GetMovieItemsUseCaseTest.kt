package com.themoviedbexample.domain.usecases

import com.themoviedbexample.domain.common.TestTransformer
import com.themoviedbexample.domain.common.TestUtils
import com.themoviedbexample.domain.common.mock
import com.themoviedbexample.domain.repositories.MovieRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetMovieItemsUseCaseTest {

    private val mRepository: MovieRepository = mock<MovieRepository>()


    @Before
    fun setUp() {

    }

    @Test
    fun getMovieItems() {
        val movieSourceEntity = TestUtils.getTestMovieSourceEntity(20)
        val getMovieItemsUseCase = GetMovieItemsUseCase(TestTransformer(), mRepository)

        Mockito.`when`(mRepository.getMovieItems()).thenReturn(Observable.just(movieSourceEntity))

        getMovieItemsUseCase.getMovieItems().test()
            .assertValue { returnedMovieEntity ->
                returnedMovieEntity.page == 0
                returnedMovieEntity.results == movieSourceEntity.results
            }
            .assertComplete()
    }
}