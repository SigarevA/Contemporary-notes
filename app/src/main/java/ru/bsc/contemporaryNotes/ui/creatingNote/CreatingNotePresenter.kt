package ru.bsc.contemporaryNotes.ui.creatingNote

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.*
import ru.bsc.contemporaryNotes.model.Note
import ru.bsc.contemporaryNotes.repositories.NoteRepo
import java.util.*
import kotlin.coroutines.CoroutineContext

class CreatingNotePresenter(
    private val view: CreatingView,
    private val noteRepo: NoteRepo
) {

    fun processClickInfo() {
        view.navigateToAbout()
    }

    fun shareData(title: String, description: String) {
        checkData(title, description) {
            view.shareNote(Note(1, title, description, Date()))
        }
    }

    fun save(scope: CoroutineScope, title: String, description: String) {
        checkData(title, description) {
            scope.launch {
                noteRepo.addNote(Note(-1, title, description, Date()))
            }
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


class PresenterScope : DefaultLifecycleObserver, CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.Main

    override fun onDestroy(owner: LifecycleOwner) {
        cancel()
        super.onDestroy(owner)
    }
}