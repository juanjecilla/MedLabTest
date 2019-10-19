package com.medlab.medlabtest.di.modules

import android.app.Application
import android.content.Context
import com.medlab.medlabtest.AppClass
import com.medlab.medlabtest.R
import com.medlab.medlabtest.data.source.remote.TheMovieDbAPI
import dagger.Module
import dagger.Provides
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    @JvmStatic
    fun provideContext(app : Application) : Context = app

    @Provides
    @Singleton
    @JvmStatic
    fun provideRealm(): Realm = Realm.getDefaultInstance()

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofit(context: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url_service))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @JvmStatic
    fun provideRetrofitAPI(retrofit: Retrofit): TheMovieDbAPI {
        return retrofit.create<TheMovieDbAPI>(TheMovieDbAPI::class.java)
    }
}