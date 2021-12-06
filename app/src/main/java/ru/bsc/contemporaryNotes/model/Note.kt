package ru.bsc.contemporaryNotes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
class Note(
    val id : Long,
    val title: String,
    val description: String,
    val dateOfCreation: Date,
    val atReminder: Date
) : Parcelable