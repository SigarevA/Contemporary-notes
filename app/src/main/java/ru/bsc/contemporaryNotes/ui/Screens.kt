package ru.bsc.contemporaryNotes.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNoteFragment

object Screens {
    fun CreatingNote() = FragmentScreen { CreatingNoteFragment() }
}