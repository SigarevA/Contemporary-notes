package ru.bsc.contemporaryNotes.ui.decorations

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spanCount: Int,
    private val spaceSize: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                left = spaceSize - column * spaceSize / spanCount
                right = (column + 1) * spaceSize / spanCount
                bottom = spaceSize
                if (position < spanCount) outRect.top = spaceSize
            } else {
                outRect.left = column * spaceSize / spanCount
                right = spaceSize - (column + 1) * spaceSize / spanCount
                if (position >= spanCount) outRect.top = spaceSize
            }
        }
    }
}