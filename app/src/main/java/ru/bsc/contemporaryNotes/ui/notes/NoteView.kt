package ru.bsc.contemporaryNotes.ui.notes

import ru.bsc.contemporaryNotes.model.Note

interface NoteView {
    fun renderLoading()
    fun renderSuccess(notes: List<Note>)
    fun renderError()
    fun renderOnClick(note: Note)
}