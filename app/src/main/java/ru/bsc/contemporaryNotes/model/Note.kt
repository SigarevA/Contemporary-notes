package ru.bsc.contemporaryNotes.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Note(
    val id: Long,
    val title: String,
    val description: String,
    val dateOfCreation: Date
) : Parcelable