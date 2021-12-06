package ru.bsc.contemporaryNotes.ui.notes

import android.util.Log
import android.view.View
import com.xwray.groupie.viewbinding.BindableItem
import ru.bsc.contemporaryNotes.R
import ru.bsc.contemporaryNotes.databinding.ItemNoteBinding
import ru.bsc.contemporaryNotes.model.Note

class NoteItem(private val note: Note, private val onClick: (Note) -> Unit) :
    BindableItem<ItemNoteBinding>() {
    override fun bind(p0: ItemNoteBinding, p1: Int) {
        Log.d(TAG, "bind note with : $p1")
        p0.itemNoteTitle.text = note.title
        p0.itemNoteDescription.text = note.description
        p0.root.setOnClickListener {
            onClick(note)
        }
    }

    override fun getLayout(): Int = R.layout.item_note

    override fun initializeViewBinding(p0: View): ItemNoteBinding =
        ItemNoteBinding.bind(p0)

    companion object {
        private const val TAG = "NoteItem"
    }
}