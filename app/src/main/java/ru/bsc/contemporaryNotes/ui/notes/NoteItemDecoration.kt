package ru.bsc.contemporaryNotes.ui.notes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import ru.bsc.contemporaryNotes.R

class NoteItemDecoration(ctx: Context) : RecyclerView.ItemDecoration() {

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = ctx.resources.getDimension(R.dimen.item_notes_header_size)
        color = ContextCompat.getColor(ctx, R.color.col_primary_text)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val index = parent.getChildAdapterPosition(view)
        if ( index < 2) {
            outRect.top = 400
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        var isDrawingHeader = false
        var counter = 0
        for (child in parent.children) {
            Log.d(TAG, "child")
            val index = parent.getChildAdapterPosition(child)
            Log.d(TAG, "element $counter with x : ${child.x} , y : ${child.y}")
            counter++
            Log.d(TAG, "result ${index != 0 && index < 2 && isDrawingHeader}")
            if (index <= 2 && !isDrawingHeader) {
                Log.d(TAG, "draw text")
                val x = parent.paddingStart + 10
                val src = "Hello"
                Log.d(TAG, "draw text")
                Log.d(TAG, "draw text with ${x.toFloat()} , y : ${child.y - 100f}")
                c.drawText(
                    src, 0, src.length,
                    x.toFloat(), child.y - 100f, textPaint
                )
                isDrawingHeader = true
            }
        }
    }

    companion object {
        private const val TAG = "NoteItemDecoration"
    }

}