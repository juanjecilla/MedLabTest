package com.example.themoviedbexample.di.modules

import com.example.themoviedbexample.ui.main.favs.FavsFragment
import com.example.themoviedbexample.ui.main.list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    internal abstract fun provideListFragmentFactory(): MovieListFragment

    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    internal abstract fun provideFavsFragmentFactory(): FavsFragment
}

