package ru.bsc.contemporaryNotes.repositories

import ru.bsc.contemporaryNotes.model.Note

interface NoteRepo {
    suspend fun getNotes(): List<Note>
    suspend fun addNote(note: Note)
}