package com.mamontov.cattest.screens.adapters

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EndlessScrollListener(
	private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

	override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
		val layoutManager = recyclerView.layoutManager as? LinearLayoutManager? ?: return

		val visibleItemCount = layoutManager.childCount
		val pastVisibleItem = layoutManager.findFirstVisibleItemPosition()
		val totalItemCount = layoutManager.itemCount

		if ((visibleItemCount + pastVisibleItem) >= totalItemCount && dy > 0) {
			onLoadMore()
		}
	}
}
