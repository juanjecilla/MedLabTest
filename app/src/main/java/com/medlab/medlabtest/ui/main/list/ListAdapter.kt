package com.medlab.medlabtest.ui.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.medlab.medlabtest.R
import com.medlab.medlabtest.base.BaseItemList
import com.medlab.medlabtest.base.BaseListViewHolder
import com.medlab.medlabtest.data.model.DummyItem
import com.medlab.medlabtest.data.model.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_vertical.view.*
import kotlinx.android.synthetic.main.item_loading.view.*
import java.util.ArrayList

class ListAdapter (
    private val mData: ArrayList<BaseItemList>,
    private val mListener: ListContract.View
) :
    RecyclerView.Adapter<BaseListViewHolder<*>>() {

    var mShouldShowLoading = true
    var mShouldShowHeader = true

    init {
        initAdapterItems()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder<*> {
        return when (viewType) {
            TYPE_MOVIE_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_vertical, parent, false)
                ListViewHolder(view)
            }
            TYPE_LOADING_ITEM -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseListViewHolder<*>, position: Int) {
        val element = mData[position]
        when (holder) {
            is ListViewHolder -> holder.bindItem(element as MovieItem)
            is LoadingViewHolder -> holder.bindItem(DummyItem())
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (mData[position]) {
            is MovieItem -> TYPE_MOVIE_ITEM
            is DummyItem -> TYPE_LOADING_ITEM
            else -> throw IllegalArgumentException("Invalid type of mData $position")
        }
    }

    fun add(item: MovieItem) {
        val prevSize = mData.size - 1
        this.mData.add(prevSize, item)
        notifyItemInserted(prevSize)
    }

    fun addAll(items: ArrayList<MovieItem>) {
        if (items.size > 0) {
            val prevSize = mData.size - 1
            this.mData.addAll(prevSize, items)
            notifyItemRangeInserted(prevSize, items.size)
        }
    }

    fun remove(item: MovieItem) {

        if (mData.contains(item)) {
            val index = mData.indexOf(item)
            mData.remove(item)
            notifyItemRemoved(index)
        }
    }

    fun clear() {
        val size = itemCount
        mData.clear()
        notifyItemRangeRemoved(0, size)
        initAdapterItems()
    }

    fun notifyItemUpdated(item: MovieItem) {
        if (mData.contains(item)) {
            val index = mData.indexOf(item)
            notifyItemChanged(index)
        }
    }

    private fun initAdapterItems() {
        mData.add(DummyItem())
        notifyDataSetChanged()
    }


    inner class ListViewHolder(itemView: View) : BaseListViewHolder<MovieItem>(itemView),
        View.OnClickListener {

        private lateinit var mMovieItem: MovieItem

        override fun onClick(view: View?) {
            if (view?.id == R.id.item_fav) {
                mListener.updateFav(mMovieItem)
            } else {
                mListener.showMovieDetail(mMovieItem)
            }
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun bindItem(item: MovieItem) {
            this.mMovieItem = item

            Picasso.get().load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + item.posterPath).into(itemView.item_image)
            itemView.item_title.text = item.title


            if (item.isFavorite) {
                itemView.item_fav.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_heart_white_on
                    )
                )
            } else {
                itemView.item_fav.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        R.drawable.ic_heart_white_off
                    )
                )
            }
        }
    }

    inner class LoadingViewHolder(itemView: View) : BaseListViewHolder<DummyItem>(itemView) {
        override fun bindItem(item: DummyItem) {
            if (!mShouldShowLoading) {
                itemView.item_progress_bar.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val TYPE_MOVIE_ITEM = 0
        private const val TYPE_LOADING_ITEM = 1
    }
}
