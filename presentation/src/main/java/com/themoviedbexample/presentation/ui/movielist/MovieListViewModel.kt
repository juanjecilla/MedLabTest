package com.themoviedbexample.presentation.ui.movielist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.themoviedbexample.domain.common.Mapper
import com.themoviedbexample.domain.entities.MovieSourcesEntity
import com.themoviedbexample.domain.usecases.GetMovieItemsUseCase
import com.themoviedbexample.presentation.common.BaseViewModel
import com.themoviedbexample.presentation.entities.Data
import com.themoviedbexample.presentation.entities.Error
import com.themoviedbexample.presentation.entities.MovieSources
import com.themoviedbexample.presentation.entities.Status

class MovieListViewModel(
    private val mGetMovieItemsUseCase: GetMovieItemsUseCase,
    private val mMapper: Mapper<MovieSourcesEntity, MovieSources>
) : BaseViewModel() {

    companion object {
        private const val TAG = "MovieListViewModel"
    }

    var mMovieItems = MutableLiveData<Data<MovieSources>>()

    fun fetchMovieItems() {
        val disposable = mGetMovieItemsUseCase.getMovieItems()
            .flatMap { mMapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mMovieItems.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called " + error.message)
                mMovieItems.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getMovieItemsLiveData() = mMovieItems
}