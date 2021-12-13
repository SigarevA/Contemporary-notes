package ru.bsc.contemporaryNotes.ui.creatingNote

import ru.bsc.contemporaryNotes.model.Note
import java.util.*

class CreatingNotePresenter(
    private val view: CreatingView
) {
    fun processClickInfo() {
        view.navigateToAbout()
    }

    fun shareData(title: String, description: String) {
        checkData(title, description) {
            view.shareNote(Note(1, title, description, Date(), Date()))
        }
    }

    fun save(title: String, description: String) {
        checkData(title, description) {
            view.saveSuccess()
        }
    }

    private inline fun checkData(title: String, description: String, doSuccess: () -> Unit) {
        if (title.isEmpty() || description.isEmpty()) {
            view.inappropriateData()
        } else {
            doSuccess()
        }
    }

    companion object {
        private const val TAG = "CreatingNotePresenter"
    }
}