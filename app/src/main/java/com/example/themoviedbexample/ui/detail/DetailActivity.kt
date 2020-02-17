package com.example.themoviedbexample.ui.detail

import android.os.Bundle
import com.example.themoviedbexample.R
import com.example.themoviedbexample.base.BaseActivity
import com.example.themoviedbexample.data.model.MovieDetail
import com.example.themoviedbexample.utils.Properties
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), DetailContract.View {

    @Inject
    lateinit var mPresenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val movieId = intent.getLongExtra(Properties.EXTRA_MOVIE_ID, 0)
        mPresenter.getMovieDetail(movieId)
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
        movie_title.text = movie.title
        movie_overview.text = movie.overview
    }
}

