package ru.bsc.contemporaryNotes.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.bsc.contemporaryNotes.entities.Note

@Database(entities = [Note::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}