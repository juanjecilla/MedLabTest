package com.example.themoviedbexample.di.modules

import com.example.themoviedbexample.di.scope.ActivityScope
import com.example.themoviedbexample.ui.detail.DetailActivity
import com.example.themoviedbexample.ui.main.MainActivity
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
