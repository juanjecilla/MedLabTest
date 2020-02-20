package com.themoviedbexample.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.common.Properties
import com.themoviedbexample.presentation.entities.Status
import com.themoviedbexample.presentation.ui.movielist.MovieListViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.progress_bar.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val mMovieListViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = ""

        val movieId = intent.getLongExtra(Properties.EXTRA_MOVIE, 0)

        mMovieListViewModel.fetchMovieDetail(movieId.toString())
    }


    override fun onStart() {
        super.onStart()
        mMovieListViewModel.getMovieDetailLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    progress_bar.visibility = View.GONE
                }
                Status.LOADING -> {
                    progress_bar.visibility = View.VISIBLE
                }
                Status.SUCCESSFUL -> {
                    progress_bar.visibility = View.GONE
                }
            }
            it?.data?.let { response ->
                movie_overview.text = response.overview
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + response.posterPath)
                    .into(movie_poster)
            }
        })
    }
}
