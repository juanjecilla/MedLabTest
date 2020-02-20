package com.themoviedbexample.presentation.ui.movielist

import androidx.lifecycle.Observer
import androidx.test.runner.AndroidJUnit4
import com.themoviedbexample.domain.common.TestEntityUtils
import com.themoviedbexample.domain.common.TestTransformer
import com.themoviedbexample.domain.common.mock
import com.themoviedbexample.domain.repositories.MovieRepository
import com.themoviedbexample.domain.usecases.GetMovieItemsUseCase
import com.themoviedbexample.presentation.entities.Data
import com.themoviedbexample.presentation.entities.MovieSources
import com.themoviedbexample.presentation.entities.Status
import com.themoviedbexample.presentation.mappers.MovieSourceEntityMapper
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
    private lateinit var errorObserver: Observer<Throwable?>

    @Before
    fun setUp() {
        mMovieRepository = mock()
        val getMovieItemsUseCase = GetMovieItemsUseCase(TestTransformer(), mMovieRepository)
        mMovieListViewModel = MovieListViewModel(getMovieItemsUseCase, movieEntityMovieMapper)
        viewObserver = Mockito.mock(Observer::class.java) as Observer<Data<MovieSources>>
        errorObserver = Mockito.mock(Observer::class.java) as Observer<Throwable?>

        mMovieListViewModel.mMovieItems.observeForever(viewObserver)
    }

    @Test
    fun fetchMovieItems() {
        val movieEntities = TestEntityUtils.getTestMovieSourceEntity(20)
        Mockito.`when`(mMovieRepository.getMovieItems()).thenReturn(Observable.just(movieEntities))
        mMovieListViewModel.fetchMovieItems()

        val movies = movieEntityMovieMapper.mapFrom(movieEntities)

        Mockito.verify(viewObserver).onChanged(Data(responseType = Status.SUCCESSFUL, data = movies))
    }
}