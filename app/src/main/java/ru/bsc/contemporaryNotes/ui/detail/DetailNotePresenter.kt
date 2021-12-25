package ru.bsc.contemporaryNotes.ui.detail

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.repositories.NoteRepo

class DetailNotePresenter(
    private val view: DetailNoteView,
    private val note: Note,
    private val noteRepo: NoteRepo
) {
    fun update(scope: CoroutineScope, title: String, description: String) {
        checkData(title, description) {
            scope.launch {
                Log.d(TAG, "update note")
                noteRepo.update(note.copy(title = title, description = description))
            }
        }
    }

    private inline fun checkData(title: String, description: String, doSuccess: () -> Unit) {
        if (title.isEmpty() || description.isEmpty()) {
            view.inappropriateData()
        } else {
            doSuccess()
        }
    }

    fun processClickShare() {
        view.share(note)
    }

    fun processUpdateNote(title: String, description: String) {
        checkData(title, description) {
            view.openDialog()
        }
    }

    companion object {
        private const val TAG = "DetailNotePresenter"
    }
}