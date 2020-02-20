package com.example.themoviedbexample.ui.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.themoviedbexample.R
import com.example.themoviedbexample.base.BaseActivity
import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.data.model.MovieItem
import com.example.themoviedbexample.utils.Properties
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View {

    @Inject
    lateinit var mPresenter: DetailPresenter

    lateinit var mMovieItem: MovieItem

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        supportActionBar?.title = ""

        mMovieItem = intent.getParcelableExtra(Properties.EXTRA_MOVIE)
        mPresenter.getMovieDetail(mMovieItem.id)

        checkFavourite(mMovieItem.isFavorite)

        fab.setOnClickListener { mPresenter.updateFavorite(mMovieItem) }
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onViewActive(this)
    }

    override fun onStop() {
        mPresenter.onViewInactive()
        super.onStop()
    }

    override fun showMovieDetail(movie: MovieDetail) {
        Picasso.get().load("https://image.tmdb.org/t/p/w185_and_h278_bestv2/" + movie.posterPath)
            .into(movie_poster)
        movie_overview.text = movie.overview

        supportActionBar?.title = movie.title
    }

    override fun checkFavourite(favorite: Boolean) {
        if (favorite) {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_heart_white_on))
        } else {
            fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_heart_white_off))
        }
    }
}

