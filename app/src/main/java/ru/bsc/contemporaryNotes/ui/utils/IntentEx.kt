package ru.bsc.contemporaryNotes.ui.utils

import android.content.Intent
import ru.bsc.contemporaryNotes.model.Note

fun Note.createShareIntent(): Intent {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, description)
        type = "text/plain"
    }
    return Intent.createChooser(sendIntent, null)
}