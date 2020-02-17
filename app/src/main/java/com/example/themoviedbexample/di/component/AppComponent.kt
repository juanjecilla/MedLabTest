package com.example.themoviedbexample.di.component

import android.app.Application
import com.example.themoviedbexample.AppClass
import com.example.themoviedbexample.di.modules.ActivitiesModule
import com.example.themoviedbexample.di.modules.AppModule
import com.example.themoviedbexample.di.modules.FragmentsModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class,
        FragmentsModule::class]
)
interface AppComponent {

    fun inject(appClass: AppClass)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}