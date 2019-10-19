package com.medlab.medlabtest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.medlab.medlabtest.base.BaseItemList
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import kotlinx.android.parcel.Parcelize

@RealmClass
@Parcelize
open class MovieDetail(
    @PrimaryKey
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("overview")
    var overview: String,
    var isFavorite: Boolean
) : BaseItemList, Parcelable, RealmModel {

    constructor() :
            this(0, "", "", "", false)
}
