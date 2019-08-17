package com.whackyapps.pallavgrover.pallav_starwars_challenge.ui.base

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE
import android.util.Log

abstract class RecyclerViewPaginator(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {

    private val batchSize = 10

    private val threshold = 2

    private val layoutManager: RecyclerView.LayoutManager?


    abstract val isLastPage: Boolean

    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            val visibleItemCount = layoutManager!!.childCount
            val totalItemCount = recyclerView.adapter!!.itemCount

            var firstVisibleItemPosition = 0
            if (layoutManager is LinearLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

            } else if (layoutManager is GridLayoutManager) {
                firstVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            }

            if(totalItemCount != 0) {
                if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {

                    val currentPage = totalItemCount / batchSize
                    Log.d("paginator", "currentPage: $currentPage")
                    loadMore(currentPage)
                }
            }

        }
    }

    abstract fun loadMore(currentPage: Int)

}