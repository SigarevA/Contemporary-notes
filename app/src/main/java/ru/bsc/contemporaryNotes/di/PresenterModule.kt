package ru.bsc.contemporaryNotes.di

import kotlinx.coroutines.CoroutineScope
import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNotePresenter
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingView
import ru.bsc.contemporaryNotes.ui.notes.NotePresenter
import ru.bsc.contemporaryNotes.ui.notes.NoteView

val presenterModule = DI.Module(name = "PresenterModule") {
    bindFactory { view: CreatingView ->
        CreatingNotePresenter(
            instance(),
            view
        )
    }
    bindFactory { params: NoteParams ->
        NotePresenter(
            params.view,
            params.scope,
            instance()
        )
    }
}

data class NoteParams(val view: NoteView, val scope: CoroutineScope)