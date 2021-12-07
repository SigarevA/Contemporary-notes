package ru.bsc.contemporaryNotes.ui.notes

import android.view.View
import android.view.ViewGroup
import androidx.core.content.PackageManagerCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.xwray.groupie.viewbinding.BindableItem
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.ItemNoteHeaderBinding

class HeaderItem : BindableItem<ItemNoteHeaderBinding?>() {
    override fun bind(p0: ItemNoteHeaderBinding, p1: Int) {
        p0.root.updateLayoutParams<StaggeredGridLayoutManager.LayoutParams> {
            isFullSpan = true
        }
    }

    override fun getLayout(): Int = R.layout.item_note_header

    override fun initializeViewBinding(p0: View): ItemNoteHeaderBinding =
        ItemNoteHeaderBinding.bind(p0)
}