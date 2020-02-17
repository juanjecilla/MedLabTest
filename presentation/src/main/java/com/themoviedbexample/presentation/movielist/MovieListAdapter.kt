package com.themoviedbexample.presentation.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.entities.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    var mData = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: MovieItem) {
            with(itemView) {
                item_title.text = item.title
                Picasso.get().load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + item.posterPath)
                    .into(item_image)

            }
        }
    }

    fun updateList(list: List<MovieItem>) {
        if (list.isNotEmpty()) {
            mData.clear()
            mData.addAll(list)
            notifyDataSetChanged()
        }
    }
}