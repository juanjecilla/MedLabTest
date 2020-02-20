package com.example.themoviedbexample.di.modules

import com.example.themoviedbexample.ui.main.list.MovieListContract
import com.example.themoviedbexample.ui.main.list.MovieListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListFragmentModule {

    @Provides
    fun provideListPresenter(movieListPresenter: MovieListPresenter): MovieListContract.Presenter {
        return movieListPresenter
    }
}