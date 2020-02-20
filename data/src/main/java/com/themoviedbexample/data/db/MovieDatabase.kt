package com.themoviedbexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.themoviedbexample.data.entitites.MovieDetailData
import com.themoviedbexample.data.entitites.MovieItemData

@Database(entities = [MovieItemData::class, MovieDetailData::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMoviesDao(): MovieDao
}