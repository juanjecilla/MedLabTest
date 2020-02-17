package com.themoviedbexample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themoviedbexample.data.entitites.MovieItemData
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("Select * from news_articles")
    fun getAllArticles(): Flowable<List<MovieItemData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<MovieItemData>)

    @Query("DELETE FROM news_articles")
    fun clear()

}