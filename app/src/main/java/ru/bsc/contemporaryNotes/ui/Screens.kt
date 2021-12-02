package ru.bsc.contemporaryNotes.ui

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNoteFragment
import ru.bsc.contemporaryNotes.ui.info.InfoFragment

object Screens {
    fun CreatingNote() = FragmentScreen { CreatingNoteFragment() }
    fun Info() = FragmentScreen { InfoFragment() }
}