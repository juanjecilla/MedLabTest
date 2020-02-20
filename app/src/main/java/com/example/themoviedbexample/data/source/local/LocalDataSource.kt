package com.example.themoviedbexample.data.source.local

import com.example.themoviedbexample.data.callbacks.OnDefaultCallback
import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.data.model.messages.FavUpdatedMessage
import com.example.themoviedbexample.data.source.DataSource
import io.realm.Realm
import org.greenrobot.eventbus.EventBus
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LocalDataSource @Inject constructor(private val mRealm: Realm) {

    fun getFavorites(callback: DataSource.FavouriteCallback) {
        val results = ArrayList<MovieItem>(
            mRealm.where(MovieItem::class.java).equalTo(
                "isFavorite",
                true
            ).findAll()
        )
        callback.onSuccess(results)
    }

    fun toggleFavorite(movieItem: MovieItem) {
        mRealm.beginTransaction()
        movieItem.isFavorite = !movieItem.isFavorite
        mRealm.copyToRealmOrUpdate(movieItem)
        mRealm.commitTransaction()

        EventBus.getDefault().post(FavUpdatedMessage(UUID.randomUUID().toString(), movieItem))
    }

    fun toggleFavorite(movieItem: MovieItem, callback: OnDefaultCallback<Boolean>) {
        mRealm.beginTransaction()
        movieItem.isFavorite = !movieItem.isFavorite
        mRealm.copyToRealmOrUpdate(movieItem)
        mRealm.commitTransaction()

        EventBus.getDefault().post(FavUpdatedMessage(UUID.randomUUID().toString(), movieItem))

        callback.onSuccess(movieItem.isFavorite)
    }

    fun isFavorite(movieItem: MovieItem): Boolean {
        val item = mRealm.where(MovieItem::class.java).equalTo("id", movieItem.id).findFirst()
        return item != null
    }
}