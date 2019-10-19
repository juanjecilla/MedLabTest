package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.main.favs.FavsFragment
import com.medlab.medlabtest.ui.main.list.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    internal abstract fun provideListFragmentFactory(): MovieListFragment

    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    internal abstract fun provideFavsFragmentFactory(): FavsFragment
}

