package com.medlab.medlabtest.ui.main.favs

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.medlab.medlabtest.R
import com.medlab.medlabtest.base.BaseScrollingView
import com.medlab.medlabtest.data.callbacks.OnMovieItemListener
import com.medlab.medlabtest.data.model.MovieItem
import com.medlab.medlabtest.data.model.messages.FavUpdatedMessage
import com.medlab.medlabtest.ui.detail.DetailActivity
import com.medlab.medlabtest.ui.main.list.MovieListAdapter
import com.medlab.medlabtest.utils.Properties
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_favs.*
import kotlinx.android.synthetic.main.fragment_favs.view.*
import kotlinx.android.synthetic.main.fragment_movie_list.empty_list
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import javax.inject.Inject

class FavsFragment : BaseScrollingView(), FavsContract.View, OnMovieItemListener {

    @Inject
    lateinit var mPresenter: FavsPresenter

    private lateinit var mAdapter: MovieListAdapter
    private var mLayoutManager = LinearLayoutManager(
        context,
        LinearLayoutManager.VERTICAL,
        false
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)

        mAdapter = MovieListAdapter(arrayListOf(), this)
        mAdapter.mShouldShowLoading = false
    }

    override fun onStart() {
        super.onStart()
        mPresenter.onViewActive(this)
    }

    override fun onStop() {
        mPresenter.onViewInactive()
        super.onStop()
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.favs_list.adapter = mAdapter
        view.favs_list.isNestedScrollingEnabled = false
        view.favs_list.layoutManager = mLayoutManager

        setProgressBar(true)
    }

    override fun onResume() {
        super.onResume()
        mPresenter.getFavourites()
    }

    override fun showFavorites(movieItems: ArrayList<MovieItem>) {
        mAdapter.clear()
        mAdapter.addAll(movieItems)
    }

    override fun shouldShowPlaceholderText() {
        if (mAdapter.itemCount > 1) {
            empty_list.visibility = View.GONE
            favs_list.visibility = View.VISIBLE
        } else {
            empty_list.visibility = View.VISIBLE
            favs_list.visibility = View.GONE
        }
    }

    override fun showMovieDetail(movieItem: MovieItem) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra(Properties.EXTRA_MOVIE_ID, movieItem)
        startActivity(intent)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: FavUpdatedMessage) {
        val item = event.item

        if (item.isFavorite) {
            mAdapter.add(item)
        } else {
            mAdapter.remove(item)
        }
    }

    override fun resetState() {
        favs_list.smoothScrollToPosition(0)
    }

    override fun updateFav(movieItem: MovieItem) {
        mPresenter.updateFavourite(movieItem)
    }

    companion object {

        fun newInstance(): FavsFragment {
            val fragment = FavsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}