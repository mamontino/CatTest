package com.mamontov.cattest.screens.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    private val spacing: Int,
    private val spanCount: Int = 2,
    private val includeEdge: Boolean = true
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        with(outRect){
            if (includeEdge) {
                initSpacingWithEdge(column, position)
            } else {
                initSpacingWithoutEdge(column, position)
            }
        }
    }

    private fun Rect.initSpacingWithEdge(column: Int, position: Int) {
        left = spacing - column * spacing / spanCount
        right = (column + 1) * spacing / spanCount

        if (position < spanCount) {
            top = spacing
        }

        bottom = spacing
    }

    private fun Rect.initSpacingWithoutEdge(column: Int, position: Int) {
        left = column * spacing / spanCount
        right = spacing - (column + 1) * spacing / spanCount

        if (position >= spanCount) {
            top = spacing
        }
    }
}