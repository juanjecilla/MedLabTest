package com.themoviedbexample.data.entitites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie_detail")
data class MovieDetailData(
    @PrimaryKey @SerializedName("id") var id: Long? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("overview") var overview: String? = null
)
