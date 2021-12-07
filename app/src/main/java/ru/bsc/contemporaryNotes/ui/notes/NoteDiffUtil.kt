package ru.bsc.contemporaryNotes.ui.notes

import androidx.recyclerview.widget.DiffUtil
import ru.bsc.contemporaryNotes.model.Note

class NoteDiffUtil(private val oldNotes: List<Note>, private val newNotes: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldNotes.size

    override fun getNewListSize(): Int = newNotes.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldNotes[oldItemPosition].id == newNotes[newItemPosition].id


    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldNotes[oldItemPosition] == newNotes[newItemPosition]
}