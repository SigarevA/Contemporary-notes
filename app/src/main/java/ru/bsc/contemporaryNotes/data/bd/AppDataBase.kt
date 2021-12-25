package ru.bsc.contemporaryNotes.data.bd

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.bsc.contemporaryNotes.data.bd.converters.DateConverter
import ru.bsc.contemporaryNotes.entities.Note

@Database(
    entities = [Note::class],
    version = 2
)
@TypeConverters(DateConverter::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}