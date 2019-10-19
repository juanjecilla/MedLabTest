package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.main.list.MovieListContract
import com.medlab.medlabtest.ui.main.list.MovieListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListFragmentModule {

    @Provides
    fun provideListPresenter(movieListPresenter: MovieListPresenter): MovieListContract.Presenter {
        return movieListPresenter
    }
}