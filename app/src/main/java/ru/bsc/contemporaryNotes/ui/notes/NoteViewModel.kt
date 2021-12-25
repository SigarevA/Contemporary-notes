package ru.bsc.contemporaryNotes.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.bsc.contemporaryNotes.repositories.NoteRepo

class NoteViewModel(
    private val noteRepo: NoteRepo
) : ViewModel() {

    private val _notes = MutableStateFlow(true)
    private val isLoading = MutableStateFlow(false)
    private val isError = MutableStateFlow<String?>(null)

    fun loadData() {
        viewModelScope.launch {
            isLoading.emit(true)
            try {
                val notes = noteRepo.getNotes()

                noteView.renderSuccess(notes)
            } catch (ex: Exception) {
                noteView.renderError()
            }

        }
    }
}