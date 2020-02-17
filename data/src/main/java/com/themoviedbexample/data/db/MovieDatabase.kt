package com.themoviedbexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.themoviedbexample.data.entitites.MovieItemData

@Database(entities = [MovieItemData::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getArticlesDao(): MovieDao
}