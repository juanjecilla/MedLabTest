package com.themoviedbexample.presentation.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.themoviedbexample.domain.common.Mapper
import com.themoviedbexample.domain.entities.MovieDetailEntity
import com.themoviedbexample.domain.usecases.GetMovieDetailUseCase
import com.themoviedbexample.presentation.common.BaseViewModel
import com.themoviedbexample.presentation.entities.Data
import com.themoviedbexample.presentation.entities.Error
import com.themoviedbexample.presentation.entities.MovieDetail
import com.themoviedbexample.presentation.entities.Status

class DetailViewModel(
    private val mGetMovieDetailUseCase: GetMovieDetailUseCase,
    private val mMapper: Mapper<MovieDetailEntity, MovieDetail>
) : BaseViewModel() {

    companion object {
        private const val TAG = "DetailViewModel:"
    }

    var mMovieDetail = MutableLiveData<Data<MovieDetail>>()

    fun fetchMovieDetail(id:String) {
        val disposable = mGetMovieDetailUseCase.getMovieDetail(id)
            .flatMap { mMapper.Flowable(it) }
            .subscribe({ response ->
                Log.d(TAG, "On Next Called")
                mMovieDetail.value = Data(responseType = Status.SUCCESSFUL, data = response)
            }, { error ->
                Log.d(TAG, "On Error Called")
                mMovieDetail.value = Data(responseType = Status.ERROR, error = Error(error.message))
            }, {
                Log.d(TAG, "On Complete Called")
            })

        addDisposable(disposable)
    }

    fun getMovieDetailLiveData() = mMovieDetail
}