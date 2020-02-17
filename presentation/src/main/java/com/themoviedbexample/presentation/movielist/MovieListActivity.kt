package com.themoviedbexample.presentation.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.entities.Status
import kotlinx.android.synthetic.main.activity_movie_list.*
import kotlinx.android.synthetic.main.content_movie_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListActivity : AppCompatActivity() {

    private val mMovieListViewModel: MovieListViewModel by viewModel()
    private lateinit var listAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        listAdapter = MovieListAdapter()

        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view.adapter = listAdapter

        mMovieListViewModel.fetchMovieItems()
    }

    override fun onStart() {
        super.onStart()
        mMovieListViewModel.getMovieItemsLiveData().observe(this, Observer {
            when (it?.responseType) {
                Status.ERROR -> {
                    //Error handling
                }
                Status.LOADING -> {
                    //Progress
                }
                Status.SUCCESSFUL -> {
                    // On Successful response
                }
            }
            it?.data?.let { response ->
                listAdapter.updateList(response.results)
            }
        })
    }
}