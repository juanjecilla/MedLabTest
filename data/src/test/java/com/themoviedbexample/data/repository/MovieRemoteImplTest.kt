package com.themoviedbexample.data.repository

import com.themoviedbexample.data.api.RemoteMovieApi
import com.themoviedbexample.data.mappers.MovieDataEntityMapper
import com.themoviedbexample.data.utils.TestEntityUtils
import com.themoviedbexample.domain.common.mock
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyLong

import org.mockito.Mockito

class MovieRemoteImplTest {

    private lateinit var mApi: RemoteMovieApi
    private lateinit var mMovieRemoteImpl: MovieRemoteImpl
    private lateinit var mMapper : MovieDataEntityMapper

    @Before
    fun before() {
        mApi = mock<RemoteMovieApi>()
        mMovieRemoteImpl = MovieRemoteImpl(mApi)
        mMapper = MovieDataEntityMapper()
    }

    @Test
    fun testGetMovieDetail(){
        val movieDetail = TestEntityUtils.getTestMovieEntity(123)
        Mockito.`when`(mApi.getMovieDetail(anyLong())).thenReturn(Observable.just(movieDetail))

        mMovieRemoteImpl.getMovieDetail(123).test()
            .assertValue { mMapper.mapToEntity(movieDetail) == it}
            .assertComplete()
    }

    @Test
    fun testGetMovieItems(){

        val movieItemSource = TestEntityUtils.getTestMovieSourceEntity(20)
        Mockito.`when`(mApi.getMovieItems()).thenReturn(Observable.just(movieItemSource))

        mMovieRemoteImpl.getMovieItems().test()
            .assertValue { mMapper.mapToEntity(movieItemSource) == it}
            .assertComplete()
    }
}