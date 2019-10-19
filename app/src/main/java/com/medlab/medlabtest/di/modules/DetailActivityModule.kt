package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.detail.DetailContract
import com.medlab.medlabtest.ui.detail.DetailPresenter
import dagger.Module
import dagger.Provides

@Module
class DetailActivityModule {

    @Provides
    fun provideDetailPresenter(detailPresenter: DetailPresenter): DetailContract.Presenter {
        return detailPresenter
    }
}
