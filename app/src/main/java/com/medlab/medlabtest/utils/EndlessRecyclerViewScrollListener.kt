package com.medlab.medlabtest.utils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.util.concurrent.atomic.AtomicBoolean

abstract class EndlessRecyclerViewScrollListener(
    layoutManager: LinearLayoutManager,
    private var startingPageIndex: Int
) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private var currentPage = startingPageIndex
    private var previousTotalItemCount = 0
    private val loading: AtomicBoolean = AtomicBoolean(true)

    private var layoutManager: RecyclerView.LayoutManager = layoutManager

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.itemCount

        when (layoutManager) {
            is StaggeredGridLayoutManager -> {
                val lastVisibleItemPositions =
                    (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(
                        null
                    )
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
            }
            is GridLayoutManager -> lastVisibleItemPosition =
                (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            is LinearLayoutManager -> lastVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex
            this.previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                this.loading.set(true)
            }
        }
        if (loading.get() && totalItemCount > (previousTotalItemCount + visibleThreshold)) {
            loading.set(false)
            previousTotalItemCount = totalItemCount
        } else {
            if (lastVisibleItemPosition + visibleThreshold > totalItemCount
                && !loading.getAndSet(true)
            ) {
                onLoadMore(++currentPage, totalItemCount, view)
            }
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)
}
