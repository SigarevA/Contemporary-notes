package ru.bsc.contemporaryNotes.ui.notes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextPaint
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import ru.bsc.contemporaryNotes.R

class NoteItemDecoration(ctx: Context, private val spanCount: Int) : RecyclerView.ItemDecoration() {

    private val marginBottom = ctx.resources.getDimension(R.dimen.item_header_title_margin_bottom)
    private val headerTextSize = ctx.resources.getDimension(R.dimen.item_notes_header_size)
    private val marginTop =
        ctx.resources.getDimension(R.dimen.item_notes_header_size) + marginBottom + headerTextSize
    private val horizontalMargin =
        ctx.resources.getDimension(R.dimen.item_header_title_margin_horizontal)
    private val titleHeader = ctx.getString(R.string.item_notes_header)


    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = headerTextSize
        color = ContextCompat.getColor(ctx, R.color.col_primary_text)
        typeface = ResourcesCompat.getFont(ctx, R.font.inter_medium)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val index = parent.getChildAdapterPosition(view)
        if (index < spanCount) {
            outRect.top = marginTop.toInt()
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        var isDrawingHeader = false
        for (child in parent.children) {
            val index = parent.getChildAdapterPosition(child)
            if (index <= spanCount && !isDrawingHeader) {
                val x = parent.paddingStart
                c.drawText(
                    titleHeader,
                    0,
                    titleHeader.length,
                    x.toFloat() + horizontalMargin,
                    child.y - marginBottom - headerTextSize,
                    textPaint
                )
                isDrawingHeader = true
            }
        }
    }

    companion object {
        private const val TAG = "NoteItemDecoration"
    }
}