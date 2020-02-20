package com.example.themoviedbexample.data.model

import android.os.Parcelable
import com.example.themoviedbexample.base.BaseItemList
import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@RealmClass
@Parcelize
open class MovieItem(
    @PrimaryKey
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("vote_average")
    var voteAvg: Double,
    var isFavorite: Boolean
) : BaseItemList, Parcelable, RealmModel {

    constructor() :
            this(0, "", "", 0.0, false)
}


