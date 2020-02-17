package com.themoviedbexample.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.themoviedbexample.data.entitites.MovieItemData
import io.reactivex.Flowable

@Dao
interface MovieDao {

    @Query("Select * from movie_items")
    fun getAllArticles(): Flowable<List<MovieItemData>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllArticles(articles: List<MovieItemData>)

    @Query("DELETE FROM movie_items")
    fun clear()

}