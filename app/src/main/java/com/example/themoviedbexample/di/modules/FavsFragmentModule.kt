package com.example.themoviedbexample.di.modules

import com.example.themoviedbexample.ui.main.favs.FavsContract
import com.example.themoviedbexample.ui.main.favs.FavsPresenter
import dagger.Module
import dagger.Provides

@Module
class FavsFragmentModule {

    @Provides
    fun provideFavsPreseter(favsPresenter: FavsPresenter): FavsContract.Presenter {
        return favsPresenter
    }
}