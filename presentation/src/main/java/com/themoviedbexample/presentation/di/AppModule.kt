package com.themoviedbexample.presentation.di

import androidx.room.Room
import com.themoviedbexample.data.api.RemoteMovieApi
import com.themoviedbexample.data.db.MovieDatabase
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.data.mappers.MovieEntityDataMapper
import com.themoviedbexample.data.repository.MovieCacheImpl
import com.themoviedbexample.data.repository.MovieRemoteImpl
import com.themoviedbexample.data.repository.MovieRepositoryImpl
import com.themoviedbexample.domain.repositories.MovieRepository
import com.themoviedbexample.domain.usecases.GetMovieDetailUseCase
import com.themoviedbexample.domain.usecases.GetMovieItemsUseCase
import com.themoviedbexample.presentation.common.AsyncFlowableTransformer
import com.themoviedbexample.presentation.mappers.MovieDetailEntityMapper
import com.themoviedbexample.presentation.mappers.MovieSourceEntityMapper
import com.themoviedbexample.presentation.ui.detail.DetailViewModel
import com.themoviedbexample.presentation.ui.movielist.MovieListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val mRepositoryModules = module {
    single(name = "remote") { MovieRemoteImpl(api = get(API)) }
    single(name = "local") {
        MovieCacheImpl(
            database = get(DATABASE), entityToDataMapper = MovieEntityDataMapper(),
            dataToEntityMapper = MovieDataEntityMapper()
        )
    }
    single { MovieRepositoryImpl(remote = get("remote"), cache = get("local")) as MovieRepository }
}

val mUseCaseModules = module {
    factory(name = GET_MOVIE_ITEMS_USECASE) {
        GetMovieItemsUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
    factory(name = GET_MOVIE_DETAIL_USECASE) {
        GetMovieDetailUseCase(
            transformer = AsyncFlowableTransformer(),
            repositories = get()
        )
    }
}

val mNetworkModules = module {
    single(name = RETROFIT_INSTANCE) { createNetworkClient(BASE_URL) }
    single(name = API) { (get(RETROFIT_INSTANCE) as Retrofit).create(RemoteMovieApi::class.java) }
}

val mLocalModules = module {
    single(name = DATABASE) {
        Room.databaseBuilder(
            androidApplication(),
            MovieDatabase::class.java,
            "movie_items"
        ).build()
    }
}

val mViewModels = module {
    viewModel {
        MovieListViewModel(
            mGetMovieItemsUseCase = get(GET_MOVIE_ITEMS_USECASE),
            mMapper = MovieSourceEntityMapper()
        )
    }
    viewModel {
        DetailViewModel(
            mGetMovieDetailUseCase = get(GET_MOVIE_DETAIL_USECASE),
            mMapper = MovieDetailEntityMapper()
        )
    }
}

private const val BASE_URL = "https://api.themoviedb.org/"
private const val RETROFIT_INSTANCE = "Retrofit"
private const val API = "Api"
private const val GET_MOVIE_ITEMS_USECASE = "getMovieItemsUseCase"
private const val GET_MOVIE_DETAIL_USECASE = "getMovieDetailUseCase"
private const val REMOTE = "remote response"
private const val DATABASE = "database"