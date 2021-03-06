package com.simpletech.simpleassets.widget

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View

/**
 * Created by antho.firuze@gmail.com on 11/09/2019.
 */
class SpacingItemDecoration(val spanCount: Int, val spacingPx: Int, val includeEdge: Boolean) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: androidx.recyclerview.widget.RecyclerView,
        state: androidx.recyclerview.widget.RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            outRect.left = spacingPx - column * spacingPx / spanCount
            outRect.right = (column + 1) * spacingPx / spanCount

            if (position < spanCount) { // top edge
                outRect.top = spacingPx
            }
            outRect.bottom = spacingPx // item bottom
        } else {
            outRect.left = column * spacingPx / spanCount
            outRect.right = spacingPx - (column + 1) * spacingPx / spanCount
            if (position >= spanCount) {
                outRect.top = spacingPx // item top
            }
        }
    }
}