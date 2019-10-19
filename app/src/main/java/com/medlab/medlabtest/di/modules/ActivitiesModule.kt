package com.medlab.medlabtest.di.modules

import com.medlab.medlabtest.di.scope.ActivityScope
import com.medlab.medlabtest.ui.detail.DetailActivity
import com.medlab.medlabtest.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [DetailActivityModule::class])
    abstract fun bindDetailActivity(): DetailActivity
}
