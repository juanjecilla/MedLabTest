package com.medlab.medlabtest.data.source.local

import com.medlab.medlabtest.data.model.MovieItem
import com.medlab.medlabtest.data.model.messages.FavUpdatedMessage
import com.medlab.medlabtest.data.source.DataSource
import io.realm.Realm
import io.realm.Sort
import org.greenrobot.eventbus.EventBus
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class LocalDataSource @Inject constructor(private val mRealm: Realm) {

    init {
        removeUnusedFavourites()
    }

    fun getFavourites(callback: DataSource.FavouriteCallback) {
        val results = ArrayList<MovieItem>(
            mRealm.where(MovieItem::class.java).equalTo(
                "isFavorite",
                true
            ).sort("updatedAt", Sort.DESCENDING).findAll()
        )
        callback.onSuccess(results)
    }

    fun toggleFavourite(movieItem: MovieItem) {
        mRealm.beginTransaction()
        movieItem.isFavorite = !movieItem.isFavorite
        mRealm.copyToRealmOrUpdate(movieItem)
        mRealm.commitTransaction()

        EventBus.getDefault().post(FavUpdatedMessage(UUID.randomUUID().toString(), movieItem))
    }

    fun isFavorite(movieItem: MovieItem): Boolean {
        val item = mRealm.where(MovieItem::class.java).equalTo("id", movieItem.id).findFirst()
        return item != null
    }


    private fun removeUnusedFavourites() {
        mRealm.beginTransaction()
        val results = mRealm.where(MovieItem::class.java).equalTo("isFavorite", false).findAll()
        results.deleteAllFromRealm()
        mRealm.commitTransaction()
    }

    companion object {
        private val MAX_LOCAL_FAVORITES = -1
    }
}