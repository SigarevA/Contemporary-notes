package ru.bsc.contemporaryNotes.ui.detailcontainer

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteFragment

class NotesPagerAdapter(fa: FragmentActivity, private val notes: List<Note>) :
    FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = notes.size

    override fun createFragment(position: Int): Fragment =
        DetailNoteFragment.newInstance(notes[position])
}