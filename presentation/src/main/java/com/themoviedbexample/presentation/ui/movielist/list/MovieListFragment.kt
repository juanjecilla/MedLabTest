package com.themoviedbexample.presentation.ui.movielist.list

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.themoviedbexample.presentation.R
import com.themoviedbexample.presentation.common.Properties
import com.themoviedbexample.presentation.entities.MovieItem
import com.themoviedbexample.presentation.entities.Status
import com.themoviedbexample.presentation.ui.commons.EndlessRecyclerViewScrollListener
import com.themoviedbexample.presentation.ui.detail.DetailActivity
import com.themoviedbexample.presentation.ui.movielist.MovieListAdapter
import com.themoviedbexample.presentation.ui.movielist.MovieListViewModel
import com.themoviedbexample.presentation.ui.movielist.OnMovieItemListener
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListFragment : Fragment(), OnMovieItemListener {

    private val mMovieListViewModel: MovieListViewModel by viewModel()
    private lateinit var listAdapter: MovieListAdapter

    private var mLayoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )
    private var mEndlessRecyclerViewScrollListener =
        object : EndlessRecyclerViewScrollListener(mLayoutManager, STARTING_PAGE_INDEX) {

            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                Log.d(TAG, "Loading new data")
                mMovieListViewModel.fetchMovieItems(page)
            }
        }

    private var mNeedRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = MovieListAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.home_list.adapter = listAdapter
        view.home_list.isNestedScrollingEnabled = false
        view.home_list.layoutManager = mLayoutManager
        view.home_list.addOnScrollListener(mEndlessRecyclerViewScrollListener)

        mMovieListViewModel.fetchMovieItems()
    }

    override fun onStart() {
        super.onStart()
        mMovieListViewModel.getMovieItemsLiveData()
            .observe(viewLifecycleOwner, androidx.lifecycle.Observer {
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

    override fun showMovieDetail(movieItem: MovieItem) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(Properties.EXTRA_MOVIE, movieItem.id)
        startActivity(intent)
    }

    companion object {

        private const val STARTING_PAGE_INDEX = 1

        fun newInstance(): MovieListFragment {
            val fragment = MovieListFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

        private const val TAG = "MovieListFragment: "
    }
}