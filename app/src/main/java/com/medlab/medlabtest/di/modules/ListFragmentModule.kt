package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.main.list.ListContract
import com.medlab.medlabtest.ui.main.list.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class ListFragmentModule {

    @Provides
    fun provideListPresenter(listPresenter: ListPresenter): ListContract.Presenter {
        return listPresenter
    }
}