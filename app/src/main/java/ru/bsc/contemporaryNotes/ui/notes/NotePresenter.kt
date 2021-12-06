package ru.bsc.contemporaryNotes.ui.notes

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.repositories.NoteRepo

class NotePresenter(
    private val noteView: NoteView,
    private val scope: CoroutineScope,
    private val noteRepo: NoteRepo
) {
    fun loadData() {
        noteView.renderLoading()
        scope.launch {
            try {
                val notes = noteRepo.getNotes()
                noteView.renderSuccess(notes)
            } catch (ex: Exception) {
                noteView.renderError()
            }
        }
    }

    fun processOnClick(note: Note) {
        noteView.renderOnClick(note)
    }
}