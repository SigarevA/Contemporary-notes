package ru.bsc.contemporaryNotes.ui.creatingNote

import ru.bsc.contemporaryNotes.model.Note

interface CreatingView {
    fun saveSuccess()
    fun shareNote(note: Note)
    fun saveError()
    fun inappropriateData()
}