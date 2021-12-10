package ru.bsc.contemporaryNotes.data.bd

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ru.bsc.contemporaryNotes.entities.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun loadAllUsers(): Array<Note>

    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)
}