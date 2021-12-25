package ru.bsc.contemporaryNotes.repositories

import ru.bsc.contemporaryNotes.data.bd.NoteDao
import ru.bsc.contemporaryNotes.model.Note
import java.util.*

private typealias EntityNote = ru.bsc.contemporaryNotes.entities.Note

class ImplNotesRepo(private val notesDao: NoteDao) : NoteRepo {

    override suspend fun getNotes(): List<Note> =
        notesDao.loadAllNotes().map {
            Note(
                it.id,
                it.title,
                it.description,
                Date()
            )
        }

    override suspend fun addNote(note: Note) {
        notesDao.insert(
            EntityNote(
                title = note.title,
                description = note.description,
                atCreated = note.dateOfCreation
            )
        )
    }

    override suspend fun update(note: Note) {
        notesDao.update(EntityNote(note.id, note.title, note.description, note.dateOfCreation))
    }
}