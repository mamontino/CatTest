package com.mamontov.cattest.screens.adapters.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearItemDecoration(
    private val spacing: Int
) : RecyclerView.ItemDecoration() {

    private companion object {
        const val FIRST_POSITION = 0
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)

        with(outRect) {
            right = spacing
            bottom = spacing
            left = spacing

            if (position == FIRST_POSITION) {
                top = spacing
            }
        }
    }
}
