package ru.bsc.contemporaryNotes.ui.notes

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NoteDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val vh = parent.getChildViewHolder(view)
        Log.d(TAG, "vh : $vh")
        Log.d(TAG, "id : ${view.id}")
    }

    private companion object {
        private const val TAG = "NoteDecoration"
    }
}