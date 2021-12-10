package ru.bsc.contemporaryNotes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val description: String
)