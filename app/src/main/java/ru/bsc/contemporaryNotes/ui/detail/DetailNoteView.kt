package ru.bsc.contemporaryNotes.ui.detail

import ru.bsc.contemporaryNotes.model.Note

interface DetailNoteView {
    fun renderSuccessfulSave()
    fun renderFailedSave()
    fun inappropriateData()
    fun share(note: Note)
    fun openDialog()
}