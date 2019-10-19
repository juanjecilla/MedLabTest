package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    internal abstract fun provideListFragmentFactory(): ListFragment
}

