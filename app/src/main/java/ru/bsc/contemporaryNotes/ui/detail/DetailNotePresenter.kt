package ru.bsc.contemporaryNotes.ui.detail

import ru.bsc.contemporaryNotes.model.Note

class DetailNotePresenter(
    private val view: DetailNoteView,
    private val note: Note

) {
    fun update(title: String, description: String) {
        checkData(title, description) {
            view.renderSuccessfulSave()
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
}