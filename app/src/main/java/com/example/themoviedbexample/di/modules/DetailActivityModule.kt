package com.example.themoviedbexample.di.modules

import com.example.themoviedbexample.ui.detail.DetailContract
import com.example.themoviedbexample.ui.detail.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    fun provideDetailPresenter(detailPresenter: DetailPresenter): DetailContract.Presenter {
        return detailPresenter
    }
}
