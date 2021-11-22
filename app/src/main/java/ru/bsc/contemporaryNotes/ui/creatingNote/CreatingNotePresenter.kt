package ru.bsc.contemporaryNotes.ui.creatingNote

import com.github.terrakok.cicerone.Router

class CreatingNotePresenter(
    private val router: Router,
    private val view: CreatingView
) {
    fun save(title: String, description: String) {
        if (title.isEmpty() || description.isEmpty()) {
            view.inappropriateData()
        } else {
            view.saveSuccess()
        }
    }

    companion object {
        private const val TAG = "CreatingNotePresenter"
    }
}