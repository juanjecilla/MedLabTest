package com.themoviedbexample.presentation.movielist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.entities.MovieItem
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    var articles = mutableListOf<MovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movieItemItem: MovieItem) {
            with(itemView) {
                heading.text = movieItemItem.title
            }
        }
    }

    fun updateList(list: List<MovieItem>) {
        if (list.isNotEmpty()) {
            articles.clear()
            articles.addAll(list)
            notifyDataSetChanged()
        }
    }
}