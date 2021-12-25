package ru.bsc.contemporaryNotes

import android.app.Application
import ru.bsc.contemporaryNotes.di.DIHolder
import ru.bsc.contemporaryNotes.di.DIProvider

class NoteApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DIHolder.provider = DIProvider(applicationContext)
    }
}