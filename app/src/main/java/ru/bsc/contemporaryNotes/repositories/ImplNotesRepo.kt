package ru.bsc.contemporaryNotes.repositories

import ru.bsc.contemporaryNotes.data.bd.NoteDao
import ru.bsc.contemporaryNotes.model.Note
import java.util.*

private typealias EntityNote = ru.bsc.contemporaryNotes.entities.Note

class ImplNotesRepo(private val notesDao: NoteDao) : NoteRepo {

    override suspend fun getNotes(): List<Note> =
        notesDao.loadAllUsers().map {
            Note(
                it.id,
                it.title,
                it.description,
                Date(1638360751000),
                Date(1638366300000)
            )
        }

    override suspend fun addNote(note: Note) {
        notesDao.insert(EntityNote(0, note.title, note.description))
    }
}