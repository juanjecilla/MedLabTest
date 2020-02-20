package com.themoviedbexample.presentation.ui.movielist

import androidx.annotation.UiThread
import androidx.lifecycle.Observer
import androidx.test.annotation.UiThreadTest
import androidx.test.runner.AndroidJUnit4
import com.themoviedbexample.domain.repositories.MovieRepository
import com.themoviedbexample.domain.usecases.GetMovieItemsUseCase
import com.themoviedbexample.presentation.entities.Data
import com.themoviedbexample.presentation.entities.MovieSources
import com.themoviedbexample.presentation.entities.Status
import com.themoviedbexample.presentation.mappers.MovieSourceEntityMapper
import com.themoviedbexample.presentation.utils.TestEntityUtils
import com.themoviedbexample.presentation.utils.TestTransformer
import com.themoviedbexample.presentation.utils.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class MovieListViewModelTest {

    private val movieEntityMovieMapper = MovieSourceEntityMapper()
    private lateinit var mMovieListViewModel: MovieListViewModel
    private lateinit var mMovieRepository: MovieRepository
    private lateinit var viewObserver: Observer<Data<MovieSources>>

    @Before
    @UiThreadTest
    fun setUp() {
        mMovieRepository = mock()
        val getMovieItemsUseCase = GetMovieItemsUseCase(TestTransformer(), mMovieRepository)
        mMovieListViewModel = MovieListViewModel(getMovieItemsUseCase, movieEntityMovieMapper)
        viewObserver = Mockito.mock(Observer::class.java) as Observer<Data<MovieSources>>

        mMovieListViewModel.mMovieItems.observeForever(viewObserver)
    }

    @Test
    @UiThreadTest
    fun fetchMovieItems() {
        val movieEntities = TestEntityUtils.getTestMovieSourceEntity(20)
        Mockito.`when`(mMovieRepository.getMovieItems()).thenReturn(Observable.just(movieEntities))
        mMovieListViewModel.fetchMovieItems()

        val movies = movieEntityMovieMapper.mapFrom(movieEntities)

        Mockito.verify(viewObserver).onChanged(Data(responseType = Status.SUCCESSFUL, data = movies))
    }
}