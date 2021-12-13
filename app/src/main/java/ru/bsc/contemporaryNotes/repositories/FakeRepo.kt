package ru.bsc.contemporaryNotes.repositories

import ru.bsc.contemporaryNotes.model.Note
import java.util.*

class FakeRepo : NoteRepo {
    private val fakeNotes = mutableListOf<Note>().apply {
        addAll(
            listOf(
                Note(
                    1,
                    "Анжумания",
                    "50x5",
                    Date(1638360751000),
                    Date(1638360000000)
                ),
                Note(
                    2,
                    "Бегит",
                    "7км",
                    Date(1638360751000),
                    Date(1638363000000)
                ),
                Note(
                    3,
                    "Анжумания",
                    "50x5",
                    Date(1638360751000),
                    Date(1638366300000)
                ),
            )
        )
    }

    override suspend fun getNotes(): List<Note> = fakeNotes

    override suspend fun addNote(note: Note) {
        fakeNotes.add(note)
    }

    override suspend fun update(note: Note) {
        TODO("Not yet implemented")
    }
}