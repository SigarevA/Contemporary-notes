package ru.bsc.contemporaryNotes.di

import org.kodein.di.DI
import org.kodein.di.bindFactory
import org.kodein.di.instance
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingNotePresenter
import ru.bsc.contemporaryNotes.ui.creatingNote.CreatingView

val presenterModule = DI.Module(name = "PresenterModule") {
    bindFactory { view: CreatingView ->
        CreatingNotePresenter(
            instance(),
            view
        )
    }
}