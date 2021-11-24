package ru.bsc.contemporaryNotes.ui.creatingNote

import com.github.terrakok.cicerone.Router
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.Screens

class CreatingNotePresenter(
    private val router: Router,
    private val view: CreatingView
) {
    fun processClickInfo() {
        router.navigateTo(Screens.Info())
    }

    fun shareData(title: String, description: String) {
        checkData(title, description) {
            view.shareNote(Note(title, description))
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