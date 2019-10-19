package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.main.favs.FavsContract
import com.medlab.medlabtest.ui.main.favs.FavsPresenter
import dagger.Module
import dagger.Provides

@Module
class FavsFragmentModule {

    @Provides
    fun provideFavsPreseter(favsPresenter: FavsPresenter): FavsContract.Presenter {
        return favsPresenter
    }
}