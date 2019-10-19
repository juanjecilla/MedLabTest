package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.list.ListContract
import com.medlab.medlabtest.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListFragmentModule {

    @Provides
    fun provideListPresenter(listPresenter: ListPresenter): ListContract.Presenter {
        return listPresenter
    }
}