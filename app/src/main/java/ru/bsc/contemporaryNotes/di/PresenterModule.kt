package ru.bsc.contemporaryNotes.di

import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNotePresenter
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingView
import ru.bsc.contemporaryNotes.ui.detail.DetailNotePresenter
import ru.bsc.contemporaryNotes.ui.detail.DetailNoteView
import ru.bsc.contemporaryNotes.ui.notes.NotePresenter
import ru.bsc.contemporaryNotes.ui.notes.NoteView

val presenterModule = DI.Module(name = "PresenterModule") {
    bindFactory { view: CreatingView ->
        CreatingNotePresenter(view, instance())
    }
    bindFactory { params: DetailNoteParams ->
        DetailNotePresenter(params.view, params.note, instance())
    }
    bindFactory { params: NoteParams ->
        NotePresenter(
            params.view,
            params.scope,
            instance()
        )
    }
}

data class DetailNoteParams(val view: DetailNoteView, val note: Note)
data class NoteParams(val view: NoteView, val scope: CoroutineScope)